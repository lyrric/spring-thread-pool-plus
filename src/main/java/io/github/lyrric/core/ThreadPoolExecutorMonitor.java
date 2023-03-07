package io.github.lyrric.core;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;


public class ThreadPoolExecutorMonitor {

    private static final Map<String, ThreadPoolExecutorDecorator> threadPoolExecutors = new ConcurrentHashMap<>();
    /**
     * 日志存储器
     */
    private final Consumer<Object> logDataConsumer;

    public ThreadPoolExecutorMonitor(Consumer<Object> logDataConsumer) {
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
            String name = executor.getName();
            return name+"-Thread-Pool"
                    + ", activeCount:" + executor.getActiveCount()
                    + ", poolSize:" + executor.getPoolSize()
                    + ", queueSize:" + executor.getQueue().size()
                    + ", completedTaskCount:" + executor.getCompletedTaskCount()
                    + ", queueFullWarning:" + executor.getQueueFullWarning()
                    + ", waitTimeoutCount:" + executor.getWaitTimeoutCount()
                    + ", execTimeoutCount:" + executor.getExecTimeoutCount()
                    + ", totalExecTime:" + executor.getTotalExecTime()
                    + ", totalWaitTime:" + executor.getTotalWaitTime()
                    + ", coreSize:" + executor.getCorePoolSize()
                    + ", maxSize:" + executor.getMaximumPoolSize();

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
                        });
            });
        }

  }
}
