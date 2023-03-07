package io.github.lyrric.core;

import java.util.concurrent.*;

public class ThreadPoolExecutorDecoratorBuilder {

    /** 线程池唯一标识 */
    private String key;
    /** 线程池名称 */
    private String name;
    /** 任务队列长度阈值 */
    private int queueSize;
    /** 任务等待时长阈值，单位毫秒 */
    private long waitTimeout;
    /** 任务执行时间阈值，单位毫秒 */
    private long execTimeout;
    /** 核心线程池数量 */
    private Integer corePoolSize;
    /** 最大线程池数量 */
    private Integer maximumPoolSize;
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

    public ThreadPoolExecutorDecoratorBuilder queueSize(int queueSize) {
        this.queueSize = queueSize;
        return this;
    }

    public ThreadPoolExecutorDecoratorBuilder waitTimeout(long waitTimeout) {
        this.waitTimeout = waitTimeout;
        return this;
    }

    public ThreadPoolExecutorDecoratorBuilder execTimeout(long execTimeout) {
        this.execTimeout = execTimeout;
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
        return new ThreadPoolExecutorDecorator(key, name, queueSize, waitTimeout, execTimeout, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                threadFactory == null ? Executors.defaultThreadFactory() : threadFactory,
                handler == null ? defaultHandler : handler);
    }
}
