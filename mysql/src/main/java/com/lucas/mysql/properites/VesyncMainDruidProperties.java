package com.lucas.mysql.properites;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: Robert
 * @since 1.0.0
 */
@Data
@Qualifier("vesyncMainDruidProperties")
@ConfigurationProperties(prefix = "vesync-main.datasource.druid")
public class VesyncMainDruidProperties extends DruidProperties {
    @Override
    public String toString() {
        return super.toString();
    }
}
