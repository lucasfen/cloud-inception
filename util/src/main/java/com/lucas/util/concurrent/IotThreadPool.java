/*
 * projectName: cloud-common
 * date: 2019-11-15
 * copyright(c) 2017-2020 etekcity.com.cn
 */
package com.lucas.util.concurrent;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tadonis
 * @since 1.4.23
 */
@Slf4j
@Getter
public class IotThreadPool extends ThreadPoolExecutor {
    private String poolName;

    public IotThreadPool(int corePoolSize,
                         int maxPoolSize,
                         long keepAliveTime,
                         TimeUnit unit,
                         String poolName) {
        super(corePoolSize, maxPoolSize, keepAliveTime, unit,
                new ArrayBlockingQueue<>(corePoolSize * 2),
                new ThreadFactoryBuilder().setNameFormat(poolName + "-%d").build(),
                new CallerRunsPolicy());
        this.poolName = poolName;
        //MetricManager.registerThreadPoolMetric(poolName, this);
        addToGracefulShutdown();
    }


    public IotThreadPool(int corePoolSize, String poolName, RejectedExecutionHandler rejectedExecutionHandler) {
        super(corePoolSize, corePoolSize * 2, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(corePoolSize * 2),
                new ThreadFactoryBuilder().setNameFormat(poolName + "-%d").build(), rejectedExecutionHandler);
        this.poolName = poolName;
        //MetricManager.registerThreadPoolMetric(poolName, this);
        addToGracefulShutdown();
    }

    public IotThreadPool(int corePoolSize, int maxPoolSize, int maxWaiteQueueSize, int maxIdleMilliSec, String poolName,
                         RejectedExecutionHandler rejectedExecutionHandler) {
        super(corePoolSize, maxPoolSize, maxIdleMilliSec, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(maxWaiteQueueSize),
                new ThreadFactoryBuilder().setNameFormat(poolName + "-%d").build(), rejectedExecutionHandler);
        this.poolName = poolName;
        //MetricManager.registerThreadPoolMetric(poolName, this);
        addToGracefulShutdown();
    }

    public IotThreadPool(int corePoolSize, String poolName) {
        this(corePoolSize, poolName, new CallerRunsPolicy());
    }

    public IotThreadPool(IotThreadPoolConfig iotThreadPoolConfig) {
        this(iotThreadPoolConfig.getCorePoolSize(), iotThreadPoolConfig.getPoolName(), new CallerRunsPolicy());
    }

    private void addToGracefulShutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("shutting down executor {}", IotThreadPool.this.getPoolName());
            IotThreadPool.this.shutdown();
            try {
                if (!IotThreadPool.this.awaitTermination(30, TimeUnit.SECONDS)) {
                    log.info("Executor did not terminate in the specified time.");
                    List<Runnable> droppedTasks = IotThreadPool.this.shutdownNow();
                    log.info("Executor was abruptly shut down. " + droppedTasks.size() + " tasks will not be executed.");
                }
            } catch (InterruptedException e) {
                log.info("shut down executor {} interrupted", IotThreadPool.this.getPoolName());
                Thread.currentThread().interrupt();
            }
            log.info("shutdown executor {} successfully", IotThreadPool.this.getPoolName());
        }));
    }

}
