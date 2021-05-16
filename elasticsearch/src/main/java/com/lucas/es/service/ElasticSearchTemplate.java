package com.lucas.es.service;

import java.util.List;

/**
 * @author Levi
 * @date 2020/3/31/031
 */
public interface ElasticSearchTemplate {

    /**
     * save document into es
     *
     * @param index    index name
     * @param document document you wanna save
     * @return document id saved in the given index
     */
    void addDocument(String index, Object document);

    /**
     * save document into es
     *
     * @param index    index name
     * @param document documents you wanna save
     * @return document id saved in the given index
     */
    void batchAddDocument(String index, List<?> document);
}
