package com.lucas.util.concurrent;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class ScheduleExecutePoolConfigurer implements SchedulingConfigurer {

    @Resource
    ScheduleThreadPoolConfig scheduleThreadPoolConfig;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(new IotScheduleThreadPool(scheduleThreadPoolConfig));
    }

}
