package com.lucas.mysql.properites;

import lombok.Data;

/**
 * author: Robert
 * date: 2019/11/28
 */
@Data
public class DruidProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String type;
    private String filters;
    private String connectionInitSqls;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private int queryTimeOut;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;

    @Override
    public String toString() {
        return "{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", type='" + type + '\'' +
                ", filters='" + filters + '\'' +
                ", connectionInitSqls='" + connectionInitSqls + '\'' +
                ", initialSize=" + initialSize +
                ", minIdle=" + minIdle +
                ", maxActive=" + maxActive +
                ", maxWait=" + maxWait +
                ", timeBetweenEvictionRunsMillis=" + timeBetweenEvictionRunsMillis +
                ", minEvictableIdleTimeMillis=" + minEvictableIdleTimeMillis +
                ", queryTimeOut=" + queryTimeOut +
                ", validationQuery='" + validationQuery + '\'' +
                ", testWhileIdle=" + testWhileIdle +
                ", testOnBorrow=" + testOnBorrow +
                ", testOnReturn=" + testOnReturn +
                '}';
    }
}
