/*
 * projectName: cloud-common
 * date: 2019-01-21
 * copyright(c) 2017-2020 etekcity.com.cn
 */
package com.lucas.mysql;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.lucas.mysql.properites.DruidProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author lucas
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractDruidDataSourceConfig {

    public abstract String getDataSourceName();

    public abstract DruidDataSource getDataSource();

    public abstract DataSourceTransactionManager getTransactionManager(DataSource dataSource);

    public abstract SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception;

    public abstract SqlSessionTemplate getSqlSessionTemplate(SqlSessionFactory sqlSessionFactory);
}
