package io.github.lyrric.core;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;


public class ThreadPoolExecutorMonitor {

    private static final Map<String, ThreadPoolExecutorDecorator> threadPoolExecutors = new ConcurrentHashMap<>();
    /**
     * 日志存储器
     */
    private final Consumer<ThreadPoolInfo> logDataConsumer;

    public ThreadPoolExecutorMonitor(Consumer<ThreadPoolInfo> logDataConsumer) {
        this.logDataConsumer = logDataConsumer;
    }


    public static void add(String key, ThreadPoolExecutorDecorator threadPoolExecutor) {
        threadPoolExecutors.put(key, threadPoolExecutor);
    }

    /**
     * 定时器
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void task(){
        threadPoolExecutors.values().stream().map(executor -> {
            ThreadPoolInfo threadPoolInfo = new ThreadPoolInfo(executor.getKey(), executor.getName(),
                    executor.getQueue().size(), executor.getQueueTotalSize(), executor.getQueueWarningRatio(), executor.getWaitTimeoutCount(), executor.getExecTimeoutCount(),
                    executor.getTotalExecTime(), executor.getTotalWaitTime(), executor.getCorePoolSize(), executor.getMaximumPoolSize(),
                    executor.getCompletedTaskCount(), executor.getActiveCount(),executor.getWaitTimeout(),  executor.getExecTimeout());
            return threadPoolInfo;

        }).forEach(logDataConsumer);
    }
  public static void reConfigThreadPool(ThreadPoolMonitorProperties properties){
        synchronized (ThreadPoolExecutorMonitor.class){
            properties.getPool().forEach(poolProperties->{
                Optional.ofNullable(threadPoolExecutors.get(poolProperties.getKey()))
                        .ifPresent(threadPool->{
                            if (threadPool.getCorePoolSize() != poolProperties.getCorePoolSize()) {
                                threadPool.setCorePoolSize(poolProperties.getCorePoolSize());
                            }
                            if (threadPool.getMaximumPoolSize() != poolProperties.getMaximumPoolSize()) {
                                threadPool.setMaximumPoolSize(poolProperties.getMaximumPoolSize());
                            }
                            threadPool.setName(poolProperties.getName());
                            threadPool.setExecTimeout(poolProperties.getExecTimeout());
                            threadPool.setWaitTimeout(poolProperties.getWaitTimeout());
                            threadPool.setQueueWarningRatio(poolProperties.getQueueWarningRatio());

                        });
            });
        }

  }
}
