package com.lucas.mysql.properites;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: Puyol
 *
 * @since 1.0.0
 */
@Data
@Qualifier("vesyncThirdPartyDruidProperties")
@ConfigurationProperties(prefix = "vesync-third-party.datasource.druid")
public class VesyncThirdPartyDruidProperties extends DruidProperties {
    @Override
    public String toString() {
        return super.toString();
    }
}
