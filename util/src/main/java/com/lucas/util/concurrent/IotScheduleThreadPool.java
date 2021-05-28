/*
 * projectName: cloud-common
 * date: 2019-11-15
 * copyright(c) 2017-2020 etekcity.com.cn
 */
package com.lucas.util.concurrent;

import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: allengao
*/
@Slf4j
@Getter
public class IotScheduleThreadPool extends ScheduledThreadPoolExecutor {

    private String poolName;

    public IotScheduleThreadPool(int corePoolSize, String poolName) {
        this(corePoolSize, poolName, new CallerRunsPolicy());
    }

    public IotScheduleThreadPool(int corePoolSize, String poolName, RejectedExecutionHandler rejectedExecutionHandler) {
        super(corePoolSize, new ThreadFactoryBuilder().setNameFormat(poolName + "-%d").build(), rejectedExecutionHandler);
        this.poolName = poolName;
        //MetricManager.registerThreadPoolMetric(poolName, this);
        addToGracefulShutdown();
    }

    public IotScheduleThreadPool(IotThreadPoolConfig iotThreadPoolConfig) {
        this(iotThreadPoolConfig.getCorePoolSize(), iotThreadPoolConfig.getPoolName(), new CallerRunsPolicy());
    }

    private void addToGracefulShutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("shutting down executor {}", IotScheduleThreadPool.this.getPoolName());
            IotScheduleThreadPool.this.shutdown();
            try {
                if (!IotScheduleThreadPool.this.awaitTermination(30, TimeUnit.SECONDS)) {
                    log.info("Executor did not terminate in the specified time.");
                    List<Runnable> droppedTasks = IotScheduleThreadPool.this.shutdownNow();
                    log.info("Executor was abruptly shut down. " + droppedTasks.size() + " tasks will not be executed.");
                }
            } catch (InterruptedException e) {
                log.info("shut down executor {} interrupted", IotScheduleThreadPool.this.getPoolName());
                Thread.currentThread().interrupt();
            }
            log.info("shutdown executor {} successfully", IotScheduleThreadPool.this.getPoolName());
        }));
    }

}
