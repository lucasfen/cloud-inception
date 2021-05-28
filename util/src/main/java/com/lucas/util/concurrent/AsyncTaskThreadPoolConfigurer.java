/*
 * projectName: cloud-common
 * date: 2019-11-15
 * copyright(c) 2017-2020 etekcity.com.cn
 */
package com.lucas.util.concurrent;

import java.util.Arrays;
import java.util.concurrent.Executor;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

/**
 * @author tadonis
 * @since 1.4.25
 */
@Slf4j
@Configuration
public class AsyncTaskThreadPoolConfigurer implements AsyncConfigurer {

    @Resource
    private IotAsyncThreadPoolConfig config;

    @Override
    public Executor getAsyncExecutor() {
        return new IotThreadPool(config);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {// 异步任务中异常处理
        return (throwable, arg1, arg2) -> {
            log.error(String.format("error occurs when handling async task. method: %s, args: %s",
                    arg1.getName(), Arrays.toString(arg2)), throwable);
            //MetricManager.getOrRegisterStwCounter("async.task.error").increment();
        };
    }
}
