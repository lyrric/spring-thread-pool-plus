package io.github.lyrric.core;

import java.util.concurrent.*;


public class ThreadPoolExecutorDecorator extends ThreadPoolExecutor {
    /**
     * 配置中心的key
     */
    private final String key;
    /**
     * 线程池名称
     */
    private final String name;

    protected ThreadPoolExecutorDecorator(String key, String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.key = key;
        this.name = name;
        ThreadPoolExecutorMonitor.add(key, this);
    }

    public static ThreadPoolExecutorDecoratorBuilder builder(){
        return new ThreadPoolExecutorDecoratorBuilder();
    }

    public String getName() {
        return name;
    }

}
