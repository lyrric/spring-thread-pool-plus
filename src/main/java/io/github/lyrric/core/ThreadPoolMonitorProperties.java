package io.github.lyrric.core;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

@ConfigurationProperties("monitor.thread")
public class ThreadPoolMonitorProperties implements InitializingBean {

    private List<threadPoolConfig> pool = Collections.emptyList();

    public static class threadPoolConfig {
        /** 线程池唯一标识 */
        private String key;
        /** 线程池名称 */
        private String name;
        /** 任务队列使用比例阈值 */
        private Double queueWarningRatio;
        /** 任务等待时长阈值，单位毫秒 */
        private long waitTimeout;
        /** 任务执行时间阈值，单位毫秒 */
        private long execTimeout;
        /** 核心线程池数量 */
        private Integer corePoolSize;
        /** 最大线程池数量 */
        private Integer maximumPoolSize;

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

        public Integer getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(Integer corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public Integer getMaximumPoolSize() {
            return maximumPoolSize;
        }

        public void setMaximumPoolSize(Integer maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
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
    }

    public List<threadPoolConfig> getPool() {
        return pool;
    }

    public void setPool(List<threadPoolConfig> pool) {
        this.pool = pool;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("ThreadPoolMonitorProperties 初始化" + this.getPool().size());
        ThreadPoolExecutorMonitor.reConfigThreadPool(this);
    }
}
