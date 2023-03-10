package io.github.lyrric.threadplus;

import io.github.lyrric.threadplus.decorator.ThreadPoolExecutorPlus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;


public class ThreadPoolExecutorManager {

    private final Logger log = LoggerFactory.getLogger(ThreadPoolExecutorManager.class);
    private static final Map<String, ThreadPoolExecutorPlus> threadPoolExecutors = new ConcurrentHashMap<>();
    /**
     * 日志存储器
     */
    private final Consumer<ThreadPoolInfo> logDataConsumer;

    public ThreadPoolExecutorManager(Consumer<ThreadPoolInfo> logDataConsumer) {
        this.logDataConsumer = logDataConsumer;
    }


    public static void add(String key, ThreadPoolExecutorPlus threadPoolExecutor) {
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
  public static void reConfigThreadPool(ThreadPoolPlusProperties properties){
        synchronized (ThreadPoolExecutorManager.class){
            properties.getConfig().forEach(poolProperties->{
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
  @PreDestroy
  public void shutdownGracefully(){
      threadPoolExecutors.values().forEach(executor ->{
          log.info("{}-thread-pool shutdown gracefully", executor.getName());
          executor.shutdown();
      });
  }
}
