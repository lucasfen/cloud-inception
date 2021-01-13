package com.lucas.common;

import java.time.Duration;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: lucasfen
 * @create: 2021/01/11
 */
@Slf4j
public class RateAndConcurrentLimiter {

    private int maxRate;
    private int maxConcurrentLevel;
    private Semaphore concurrentLimiter;
    private RateLimiter rateLimiter;

    public RateAndConcurrentLimiter(int maxConcurrentLevel, int maxRate) {
        this.maxRate = maxRate;
        this.maxConcurrentLevel = maxConcurrentLevel;
        this.concurrentLimiter = new Semaphore(maxConcurrentLevel);
        this.rateLimiter = RateLimiter.create(maxRate);
    }

    public boolean acquireOrException(long timeoutInMs) {
        long startNs = System.nanoTime();
        boolean concurrentAcquired = false;
        boolean rateAcquired = false;
        try {
            concurrentAcquired = concurrentLimiter.tryAcquire(timeoutInMs, TimeUnit.MILLISECONDS);
            if (!concurrentAcquired) {
                log.error("concurrent limited. max concurrent level: {}, queue length: {}", maxConcurrentLevel,
                        concurrentLimiter.getQueueLength());
                throw new RuntimeException();
            }

            long remainTimeoutInNs = Duration.ofMillis(timeoutInMs).toNanos() - (System.nanoTime() - startNs);
            rateAcquired = rateLimiter.tryAcquire(remainTimeoutInNs, TimeUnit.NANOSECONDS);

            if (!rateAcquired) {
                log.error("rate limited. max rate: {}, current rate: {}", maxRate, rateLimiter.getRate());
                throw new RuntimeException();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (concurrentAcquired && !rateAcquired) {
                concurrentLimiter.release();
            }
        }
        return true;
    }

    public void release() {
        concurrentLimiter.release();
    }

    public boolean tryAcquire() {
        return concurrentLimiter.tryAcquire() && rateLimiter.tryAcquire();
    }

    public boolean tryAcquire(long timeoutInMs) {
        long startNs = System.nanoTime();
        try {
            boolean concurrentAcquired = concurrentLimiter.tryAcquire(timeoutInMs, TimeUnit.MILLISECONDS);
            if (concurrentAcquired) {
                long remainTimeoutInNs = Duration.ofMillis(timeoutInMs).toNanos() - (System.nanoTime() - startNs);
                return rateLimiter.tryAcquire(remainTimeoutInNs, TimeUnit.NANOSECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
