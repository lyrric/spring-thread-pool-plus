package io.github.lyrric.core;

import java.util.concurrent.*;

public class ThreadPoolExecutorDecoratorBuilder {

    private String key;
    private String name;
    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;
    private TimeUnit unit;
    private BlockingQueue<Runnable> workQueue;
    private ThreadFactory threadFactory;
    private RejectedExecutionHandler handler;
    private static final RejectedExecutionHandler defaultHandler =
            new ThreadPoolExecutor.AbortPolicy();

    public ThreadPoolExecutorDecoratorBuilder key(String key) {
        this.key = key;
        return this;
    }
    public ThreadPoolExecutorDecoratorBuilder name(String name) {
        this.name = name;
        return this;
    }
    public ThreadPoolExecutorDecoratorBuilder corePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public ThreadPoolExecutorDecoratorBuilder maximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    public ThreadPoolExecutorDecoratorBuilder keepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    public ThreadPoolExecutorDecoratorBuilder unit(TimeUnit unit) {
        this.unit = unit;
        return this;
    }

    public ThreadPoolExecutorDecoratorBuilder workQueue(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        return this;
    }

    public ThreadPoolExecutorDecoratorBuilder threadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    public ThreadPoolExecutorDecoratorBuilder handler(RejectedExecutionHandler handler) {
        this.handler = handler;
        return this;
    }

    public ThreadPoolExecutorDecorator build() {
        return new ThreadPoolExecutorDecorator(key, name, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                threadFactory == null ? Executors.defaultThreadFactory() : threadFactory,
                handler == null ? defaultHandler : handler);
    }
}
