package com.lucas.es.service;

public class ElasticHolder {
    private static ElasticSearchTemplate elasticSearchTemplate;

    public static ElasticSearchTemplate getElasticSearchTemplate() {
        return elasticSearchTemplate;
    }

    static void setElasticSearchTemplate(ElasticSearchTemplate es) {
        elasticSearchTemplate = es;
    }
}
