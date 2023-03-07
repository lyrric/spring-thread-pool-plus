package io.github.lyrric.core;

import io.github.lyrric.task.AbstractTaskMonitorDecorator;
import io.github.lyrric.task.RunnableMonitorDecorator;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class ThreadPoolExecutorDecorator extends ThreadPoolExecutor {
    /** 配置中心的key */
    private final String key;
    /** 线程池名称 */
    private String name;

    /** 任务队列长度阈值 */
    private int queueSize;
    /** 任务等待时长阈值，单位毫秒 */
    private long waitTimeout;
    /** 任务执行时间阈值，单位毫秒 */
    private long execTimeout;

    /** 任务队列是否长度超过阈值 */
    private final AtomicBoolean queueFullWarning = new AtomicBoolean(false);
    /** 任务等待时间超过阈值的任务数量 */
    private final AtomicInteger waitTimeoutCount = new AtomicInteger(0);
    /** 任务执行超过阈值的任务数量 */
    private final AtomicInteger execTimeoutCount = new AtomicInteger(0);
    /** 任务执行总时长 */
    private final AtomicLong totalExecTime = new AtomicLong(0);
    /** 任务等待总时长 */
    private final AtomicLong totalWaitTime = new AtomicLong(0);


    protected ThreadPoolExecutorDecorator(String key, String name, int queueSize,long waitTimeout,long execTimeout,
                                          int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.key = key;
        this.name = name;
        this.queueSize = queueSize;
        this.waitTimeout = waitTimeout;
        this.execTimeout = execTimeout;
        ThreadPoolExecutorMonitor.add(key, this);
    }


    @Override
    public void execute(Runnable command) {
        super.execute(new RunnableMonitorDecorator(command));
        //判断队列大小
        if (getQueue().size() > queueSize) {
            queueFullWarning.set(true);
        }
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        //计算等待时间、执行时间
        if (r instanceof AbstractTaskMonitorDecorator) {
            long waitTime = ((AbstractTaskMonitorDecorator) r).getWaitTime();
            totalWaitTime.addAndGet(waitTime);
            if (waitTime > waitTimeout) {
                waitTimeoutCount.incrementAndGet();
            }
            long execTime = ((AbstractTaskMonitorDecorator) r).getExecTime();
            totalExecTime.addAndGet(execTime);
            if (execTime > execTimeout) {
                execTimeoutCount.incrementAndGet();
            }
        }
    }






    public static ThreadPoolExecutorDecoratorBuilder builder(){
        return new ThreadPoolExecutorDecoratorBuilder();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public long getWaitTimeout() {
        return waitTimeout;
    }

    public void setWaitTimeout(long waitTimeout) {
        this.waitTimeout = waitTimeout;
    }

    public long getExecTimeout() {
        return execTimeout;
    }

    public void setExecTimeout(long execTimeout) {
        this.execTimeout = execTimeout;
    }

    public boolean getQueueFullWarning() {
        return queueFullWarning.getAndSet(false);
    }

    public AtomicInteger getWaitTimeoutCount() {
        return waitTimeoutCount;
    }

    public AtomicInteger getExecTimeoutCount() {
        return execTimeoutCount;
    }

    public AtomicLong getTotalExecTime() {
        return totalExecTime;
    }

    public AtomicLong getTotalWaitTime() {
        return totalWaitTime;
    }
}
