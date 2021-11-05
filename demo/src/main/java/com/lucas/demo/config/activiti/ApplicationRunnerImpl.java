package com.lucas.demo.config.activiti;

import java.io.IOException;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

/**
 * @author wayne
 * @date 2020/10/20
 */

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Autowired
    RepositoryService repositoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource[] resources = null;
        try {
            resources = new PathMatchingResourcePatternResolver().getResources("classpath:processes/*.bpmn");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Resource r : resources) {
            String addr = "processes/" + r.getFilename();
            repositoryService.createDeployment().addClasspathResource(addr).deploy();
        }
    }
}

