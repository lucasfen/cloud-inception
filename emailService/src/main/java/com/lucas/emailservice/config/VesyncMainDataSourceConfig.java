package com.lucas.emailservice.config;

import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.google.gson.Gson;
import com.lucas.mysql.properites.DruidProperties;
import com.lucas.mysql.properites.VesyncMainDruidProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


/**
 * @author vik
 * @date 2020/3/7
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(VesyncMainDruidProperties.class)
@MapperScan(basePackages = VesyncMainDataSourceConfig.PACKAGE, sqlSessionTemplateRef = VesyncMainDataSourceConfig.SQL_SESSION_TEMPLATE)
public class VesyncMainDataSourceConfig {

    static final String PACKAGE = "com.etekcity.cloud.thirdpartyservice.infra.dao.mapper.vesyncmain";

    private static final String MAPPER_LOCATION = "classpath:/mapper/vesync_main/*.xml";

    private static final String DATA_SOURCE_NAME = "vesyncMainDataSource";

    private static final String TRANSACTION_MANAGER = "vesyncMainTransactionManager";

    private static final String SQL_SESSION_FACTORY = "vesyncMainSqlSessionFactory";

    static final String SQL_SESSION_TEMPLATE = "vesyncMainSqlSessionTemplate";

    @Autowired
    private VesyncMainDruidProperties druidProperties;

    public DruidProperties getProperties() {
        return druidProperties;
    }

    public String getDataSourceName() {
        return DATA_SOURCE_NAME;
    }


    @Bean(name = DATA_SOURCE_NAME)
    @Primary
    public DruidDataSource getDataSource(@Qualifier("vesyncMainDruidProperties") DruidProperties properties) {
        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(new Gson().fromJson(druidProperties.toString(), Properties.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (DruidDataSource) dataSource;
    }

    @Bean(name = TRANSACTION_MANAGER)
    @Primary
    public DataSourceTransactionManager getTransactionManager(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = SQL_SESSION_FACTORY)
    @Primary
    public SqlSessionFactory getSqlSessionFactory(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }

//    @Bean(name = SQL_SESSION_TEMPLATE)
//    @Primary
//    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier(SQL_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

//    @PostConstruct
//    public void registerToCloudMonitor() {
//        DruidStat.register(DATA_SOURCE_NAME, this.getDataSource(getProperties()));
//        log.info(String.format("druidDataSource %s register to monitor", getDataSourceName()));
//    }
}
