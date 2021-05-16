package com.lucas.es.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lucas.es.config.ElasticSearchPool;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author Levi
 * @date 2020/3/31/031
 */
@Slf4j
@Service
public class ElasticSearchTemplateImpl implements ElasticSearchTemplate {
    private static final Type MAP_TYPE = new TypeToken<Map<String, Object>>() {
    }.getType();
    public static final Type BATCH_MAP_TYPE = new TypeToken<List<Map<String, Object>>>() {
    }.getType();

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    @Autowired
    private ElasticSearchPool elasticSearchPool;

    {
        ElasticHolder.setElasticSearchTemplate(this);
    }

    @Override
    public void addDocument(String index, Object document) {
        RestHighLevelClient restHighLevelClient = elasticSearchPool.getResource();
        try {
            Map<String, Object> documentMap = gson.fromJson(gson.toJson(document), MAP_TYPE);
            IndexRequest indexRequest = new IndexRequest(index, "_doc").source(documentMap);
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            elasticSearchPool.returnResource(restHighLevelClient);
        }
    }


    @Override
    public void batchAddDocument(String index, List<?> docs) {
        RestHighLevelClient restHighLevelClient = elasticSearchPool.getResource();
        try {
            List<Map<String, Object>> docsMap = gson.fromJson(gson.toJson(docs), BATCH_MAP_TYPE);
            BulkRequest bulkRequest = new BulkRequest();
            docsMap.forEach(docMap -> bulkRequest.add(new IndexRequest(index, "_doc").source(docMap)));
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            elasticSearchPool.returnResource(restHighLevelClient);
        }
    }

}
