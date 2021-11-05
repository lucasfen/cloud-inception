package com.lucas.demo.config.activiti;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author lucasfen
 * @date 2021/10/11
 */
@Configuration
@ConfigurationProperties(prefix = "activiti.datasource")
@Data
@Slf4j
public class ProcessEngineConfig {

    @Value("${lucas-operation.datasource.druid.url}")
    private String url;

    @Value("${lucas-operation.datasource.druid.driverClassName}")
    private String driverClassName;

    @Value("${lucas-operation.datasource.druid.username}")
    private String username;

    @Value("${lucas-operation.datasource.druid.password}")
    private String password;

    /**
     * 初始化流程引擎
     *
     * @return
     */
    @Primary
    @Bean
    public ProcessEngine initProcessEngine() {
        log.info("=============================ProcessEngineBegin=============================");

        // 流程引擎配置
        ProcessEngineConfiguration cfg = null;

        try {
            cfg = new StandaloneProcessEngineConfiguration()
                    .setJdbcUrl(url)
                    .setJdbcUsername(username)
                    .setJdbcPassword(password)
                    .setJdbcDriver(driverClassName)
                    // 初始化基础表，不需要的可以改为 DB_SCHEMA_UPDATE_FALSE
                    .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 初始化流程引擎对象
        ProcessEngine processEngine = cfg.buildProcessEngine();
        log.info("=============================ProcessEngineEnd=============================");
        return processEngine;
    }

    //八大接口
    // 业务流程的定义相关服务
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    // 流程对象实例相关服务
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    // 流程任务节点相关服务
    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    // 流程历史信息相关服务
    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    // 表单引擎相关服务
    @Bean
    public FormService formService(ProcessEngine processEngine) {
        return processEngine.getFormService();
    }

    // 用户以及组管理相关服务
    @Bean
    public IdentityService identityService(ProcessEngine processEngine) {
        return processEngine.getIdentityService();
    }

    // 管理和维护相关服务
    @Bean
    public ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    // 动态流程服务
    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine) {
        return processEngine.getDynamicBpmnService();
    }
    //八大接口 end
}