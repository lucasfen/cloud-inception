package com.lucas.mysql.properites;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Robert
 * @since 1.0.0
 */
@Data
@Qualifier("vesyncRecipeDruidProperties")
@ConfigurationProperties(prefix = "vesync-recipe.datasource.druid")
public class VesyncRecipeDruidProperties extends DruidProperties {
    @Override
    public String toString() {
        return super.toString();
    }
}
