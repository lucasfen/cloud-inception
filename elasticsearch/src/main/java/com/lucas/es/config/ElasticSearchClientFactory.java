package com.lucas.es.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import lombok.Data;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.http.HttpHost;
import org.elasticsearch.client.*;

/**
 * @author : phil
 * @date : 2020/4/4 14:49
 */
@Data
public class ElasticSearchClientFactory implements PooledObjectFactory<RestHighLevelClient> {

    private AtomicReference<Set<String>> nodesReference = new AtomicReference<>();

    private String clusterName;

    public ElasticSearchClientFactory(String clusterName, Set<String> clusterNodes) {
        this.clusterName = clusterName;
        this.nodesReference.set(clusterNodes);
    }


    private List<HttpHost> getEsClusterHosts(AtomicReference<Set<String>> nodesReference) {
        List<HttpHost> esClusterHosts = new ArrayList<>();
        nodesReference.get().forEach(host -> {
            String[] hostInfo = host.split(":");
            esClusterHosts.add(new HttpHost(hostInfo[0], Integer.valueOf(hostInfo[1]), "http"));
        });
        return esClusterHosts;
    }

    @Override
    public PooledObject<RestHighLevelClient> makeObject() throws Exception {
        RestClientBuilder restClientBuilder = RestClient.builder(getEsClusterHosts(nodesReference)
                .toArray(new HttpHost[0]));
        restClientBuilder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
        return new DefaultPooledObject(client);
    }

    @Override
    public void destroyObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {
        RestHighLevelClient client = pooledObject.getObject();
        if (client != null && client.ping(RequestOptions.DEFAULT)) {
            try {
                client.close();
            } catch (Exception e) {
                //ignore
            }
        }
    }

    @Override
    public boolean validateObject(PooledObject<RestHighLevelClient> pooledObject) {
        RestHighLevelClient client = pooledObject.getObject();
        try {
            return client.ping(RequestOptions.DEFAULT);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void activateObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {
        RestHighLevelClient client = pooledObject.getObject();
        boolean response = client.ping(RequestOptions.DEFAULT);
    }

    @Override
    public void passivateObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {
        //nothing
    }


}
