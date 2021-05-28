package com.lucas.dal.config.mysql.lucasoperation;

import java.util.Properties;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.google.gson.Gson;
import com.lucas.mysql.AbstractDruidDataSourceConfig;
import com.lucas.mysql.properites.LucasOperationDruidProperties;
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
 * @author lucas
 * @date 2021/4/23
 */
@Configuration
@EnableConfigurationProperties(LucasOperationDruidProperties.class)
@MapperScan(basePackages = LucasOperationDataSourceConfig.PACKAGE, sqlSessionTemplateRef = LucasOperationDataSourceConfig.SQL_SESSION_TEMPLATE)
public class LucasOperationDataSourceConfig extends AbstractDruidDataSourceConfig {

    static final String PACKAGE = "com.lucas.dal.mysql.mapper.lucasoperation";
    static final String SQL_SESSION_TEMPLATE = "lucasOperationSqlSessionTemplate";
    private static final String DATA_SOURCE_NAME = "lucasOperationDataSource";
    private static final String MAPPER_LOCATION = "classpath:/mapper/lucasoperation/*.xml";
    private static final String TRANSACTION_MANAGER = "lucasOperationTransactionManager";
    private static final String SQL_SESSION_FACTORY = "lucasOperationSqlSessionFactory";
    @Autowired
    private LucasOperationDruidProperties druidProperties;

    @Override
    public String getDataSourceName() {
        return DATA_SOURCE_NAME;
    }

    @Override
    @Bean(DATA_SOURCE_NAME)
    @Primary
    public DruidDataSource getDataSource() {
        DataSource dataSource;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(new Gson()
                    .fromJson(druidProperties.toString(), Properties.class));
        } catch (Exception e) {
            return null;
        }
        return (DruidDataSource) dataSource;
    }

    @Override
    @Bean(name = TRANSACTION_MANAGER)
    public DataSourceTransactionManager getTransactionManager(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory getSqlSessionFactory(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }

    @Override
    @Bean(name = SQL_SESSION_TEMPLATE)
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier(SQL_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
