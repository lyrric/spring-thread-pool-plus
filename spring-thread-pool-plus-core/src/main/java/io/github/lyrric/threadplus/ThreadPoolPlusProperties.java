package io.github.lyrric.threadplus;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

@ConfigurationProperties("thread.pool.plus")
public class ThreadPoolPlusProperties implements InitializingBean {

    private List<Config> config = Collections.emptyList();

    public static class Config {
        /** 线程池唯一标识 */
        private String key;
        /** 线程池名称 */
        private String name;
        /** 任务队列使用比例阈值 */
        private Double queueWarningRatio;
        /** 队列容量 */
        private int queueCapacity;

        /** 任务等待时长阈值，单位毫秒 */
        private long waitTimeout;
        /** 任务执行时间阈值，单位毫秒 */
        private long execTimeout;
        /** 核心线程池数量 */
        private int corePoolSize;
        /** 最大线程池数量 */
        private int maximumPoolSize;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Double getQueueWarningRatio() {
            return queueWarningRatio;
        }

        public void setQueueWarningRatio(Double queueWarningRatio) {
            this.queueWarningRatio = queueWarningRatio;
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

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public void setMaximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
        }

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public int getMaximumPoolSize() {
            return maximumPoolSize;
        }
    }

    public List<Config> getConfig() {
        return config;
    }

    public void setConfig(List<Config> config) {
        this.config = config;
    }

    @Override
    public void afterPropertiesSet() {
        ThreadPoolExecutorManager.reConfigThreadPool(this);
    }
}
