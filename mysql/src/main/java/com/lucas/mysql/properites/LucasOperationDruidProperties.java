package com.lucas.mysql.properites;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: Lucas
 * @since 1.0.0
 */
@Data
@Qualifier("LucasOperationDruidProperties")
@ConfigurationProperties(prefix = "lucas-operation.datasource.druid")
public class LucasOperationDruidProperties extends DruidProperties {
    @Override
    public String toString() {
        return super.toString();
    }
}
