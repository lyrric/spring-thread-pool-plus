package io.github.lyrric.threadplus.decorator;

import java.util.concurrent.*;

public class ThreadPoolExecutorPlusBuilder {

    /** 线程池唯一标识 */
    private String key;
    /** 线程池名称 */
    private String name;
    /** 任务队列长度使用占比阈值 */
    private Double queueWarningRatio;
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

    public ThreadPoolExecutorPlusBuilder key(String key) {
        this.key = key;
        return this;
    }
    public ThreadPoolExecutorPlusBuilder name(String name) {
        this.name = name;
        return this;
    }
    public ThreadPoolExecutorPlusBuilder corePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public ThreadPoolExecutorPlusBuilder maximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    public ThreadPoolExecutorPlusBuilder keepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    public ThreadPoolExecutorPlusBuilder unit(TimeUnit unit) {
        this.unit = unit;
        return this;
    }

    public ThreadPoolExecutorPlusBuilder workQueue(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        return this;
    }

    public ThreadPoolExecutorPlusBuilder queueWarningRatio(Double queueWarningRatio) {
        this.queueWarningRatio = queueWarningRatio;
        return this;
    }

    public ThreadPoolExecutorPlusBuilder waitTimeout(long waitTimeout) {
        this.waitTimeout = waitTimeout;
        return this;
    }

    public ThreadPoolExecutorPlusBuilder execTimeout(long execTimeout) {
        this.execTimeout = execTimeout;
        return this;
    }

    public ThreadPoolExecutorPlusBuilder threadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    public ThreadPoolExecutorPlusBuilder handler(RejectedExecutionHandler handler) {
        this.handler = handler;
        return this;
    }

    public ThreadPoolExecutorPlus build() {
        return new ThreadPoolExecutorPlus(key, name, queueWarningRatio, waitTimeout, execTimeout, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                threadFactory == null ? Executors.defaultThreadFactory() : threadFactory,
                handler == null ? defaultHandler : handler);
    }
}
