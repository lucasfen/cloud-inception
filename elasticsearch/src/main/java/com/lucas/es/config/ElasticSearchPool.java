package com.lucas.es.config;

import java.util.Set;

import com.lucas.util.pool.Pool;
import lombok.Data;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : phil
 * @date : 2020/4/4 14:49
 */
@Data
@Configuration
@EnableConfigurationProperties(ElasticSearchPoolConfig.class)
public class ElasticSearchPool extends Pool<RestHighLevelClient> {

    private String clusterName;
    private Set<String> clusterNodes;

    public ElasticSearchPool(@Qualifier(value = "elasticSearchPoolConfig") ElasticSearchPoolConfig config) {
        super(config, new ElasticSearchClientFactory(config.getClusterName(), config.getNodes()));
        this.clusterName = config.getClusterName();
        this.clusterNodes = config.getNodes();
    }
}
