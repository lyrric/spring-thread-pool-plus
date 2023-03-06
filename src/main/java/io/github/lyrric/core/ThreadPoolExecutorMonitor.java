package io.github.lyrric.core;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;


public class ThreadPoolExecutorMonitor {

    private static Map<String, ThreadPoolExecutor> threadPoolExecutors = new ConcurrentHashMap<>();
    /**
     * 日志存储器
     */
    private final Consumer<Object> logDataConsumer;

    public ThreadPoolExecutorMonitor(Consumer<Object> logDataConsumer) {
        this.logDataConsumer = logDataConsumer;
    }


    public static void add(String key, ThreadPoolExecutor threadPoolExecutor) {
        threadPoolExecutors.put(key, threadPoolExecutor);
    }

    /**
     * 监听配置变化
     */
    public void listen(){

    }

    /**
     * 定时器
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void task(){
        threadPoolExecutors.values().stream().map(executor -> {
            String name = ((ThreadPoolExecutorDecorator) executor).getName();
            return "Thread pool"
                    + " name:" + name
                    + " activeCount:" + executor.getActiveCount()
                    + " poolSize:" + executor.getPoolSize()
                    + " queueSize:" + executor.getQueue().size()
                    + " completedTaskCount:" + executor.getCompletedTaskCount()
                    + " coreSize:" + executor.getCorePoolSize()
                    + " maxSize:" + executor.getMaximumPoolSize();

        }).forEach(logDataConsumer);
    }


}
