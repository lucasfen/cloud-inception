package com.lucas.es.config;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: lucasfen
 * @create: 2021/02/05
 */
@Data
@Qualifier(value = "elasticSearchPoolConfig")
@ConfigurationProperties(prefix = "vesync.elasticsearch")
public class ElasticSearchPoolConfig extends GenericObjectPoolConfig {

    private String clusterName;

    private Set<String> nodes = new HashSet<>();

    private long connectTimeMillis;
}
