package io.github.lyrric.threadplus;

import java.time.LocalDateTime;

public class ThreadPoolInfo {
    /** 线程池唯一标识 */
    private String key;
    /** 线程池名称 */
    private String name;
    /** 任务队列是否长度超过阈值 */
    private Boolean queueFullWarning ;
    /** 任务队列使用比例阈值 */
    private Double queueWarningRatio;
    /** 任务队列预警长度 */
    private Integer queueWarningSize;
    /** 任务队列使用数量 */
    private Integer queueUseSize ;


    /** 任务等待时间超过阈值的任务数量 */
    private Long waitTimeoutCount ;
    /** 任务执行超过阈值的任务数量 */
    private Long execTimeoutCount;
    /** 任务执行总时长 */
    private Long totalExecTime;
    /** 任务平均执行总时长 */
    private Integer avgExecTime;
    /** 任务等待总时长 */
    private Long totalWaitTime ;
    /** 任务平均等待总时长 */
    private Integer avgWaitTime ;
    /** 已完成任务 */
    private Long completedTaskCount;
    /** 当前活跃的线程数量 */
    private Integer activeCount;


    /** 核心线程池数量 */
    private Integer corePoolSize;
    /** 最大线程池数量 */
    private Integer maximumPoolSize;
    /** 队列总长度 */
    private Integer queueTotalSize;
    /** 任务等待时长阈值，单位毫秒 */
    private Long waitTimeout;
    /** 任务执行时间阈值，单位毫秒 */
    private Long execTimeout;
    private Long createTime;

    public ThreadPoolInfo() {
    }


    public ThreadPoolInfo(String key, String name, Integer queueUseSize, Integer queueTotalSize, Double queueWarningRatio,
                          Long waitTimeoutCount, Long execTimeoutCount, Long totalExecTime,Long totalWaitTime,
                          Integer corePoolSize, Integer maximumPoolSize, Long completedTaskCount, Integer activeCount,
                          long waitTimeout, long execTimeout) {
        this.key = key;
        this.name = name;
        this.waitTimeoutCount = waitTimeoutCount;
        this.execTimeoutCount = execTimeoutCount;
        this.totalExecTime = totalExecTime;
        if (completedTaskCount != 0) {
            this.avgExecTime = Math.toIntExact(totalExecTime / completedTaskCount);
            this.avgWaitTime = Math.toIntExact(totalWaitTime / completedTaskCount);
        }
        this.totalWaitTime = totalWaitTime;
        this.activeCount = activeCount;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.queueTotalSize = queueTotalSize;
        this.queueUseSize = queueUseSize;
        this.queueWarningRatio = queueWarningRatio;
        this.queueWarningSize = (int)(queueTotalSize * queueWarningRatio);
        this.queueFullWarning = queueUseSize > queueWarningSize;
        this.completedTaskCount = completedTaskCount;
        this.waitTimeout = waitTimeout;
        this.execTimeout = execTimeout;
        this.createTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "ThreadPoolInfo{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", queueFullWarning=" + queueFullWarning +
                ", queueWarningRatio=" + queueWarningRatio +
                ", queueWarningSize=" + queueWarningSize +
                ", queueUseSize=" + queueUseSize +
                ", waitTimeoutCount=" + waitTimeoutCount +
                ", execTimeoutCount=" + execTimeoutCount +
                ", totalExecTime=" + totalExecTime +
                ", avgExecTime=" + avgExecTime +
                ", totalWaitTime=" + totalWaitTime +
                ", avgWaitTime=" + avgWaitTime +
                ", completedTaskCount=" + completedTaskCount +
                ", activeCount=" + activeCount +
                ", corePoolSize=" + corePoolSize +
                ", maximumPoolSize=" + maximumPoolSize +
                ", queueTotalSize=" + queueTotalSize +
                ", waitTimeout=" + waitTimeout +
                ", execTimeout=" + execTimeout +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getQueueWarningSize() {
        return queueWarningSize;
    }

    public void setQueueWarningSize(Integer queueWarningSize) {
        this.queueWarningSize = queueWarningSize;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getQueueFullWarning() {
        return queueFullWarning;
    }

    public void setQueueFullWarning(Boolean queueFullWarning) {
        this.queueFullWarning = queueFullWarning;
    }

    public Integer getQueueUseSize() {
        return queueUseSize;
    }

    public void setQueueUseSize(Integer queueUseSize) {
        this.queueUseSize = queueUseSize;
    }

    public Integer getQueueTotalSize() {
        return queueTotalSize;
    }

    public void setQueueTotalSize(Integer queueTotalSize) {
        this.queueTotalSize = queueTotalSize;
    }

    public Double getQueueWarningRatio() {
        return queueWarningRatio;
    }

    public void setQueueWarningRatio(Double queueWarningRatio) {
        this.queueWarningRatio = queueWarningRatio;
    }

    public Long getWaitTimeoutCount() {
        return waitTimeoutCount;
    }

    public void setWaitTimeoutCount(Long waitTimeoutCount) {
        this.waitTimeoutCount = waitTimeoutCount;
    }

    public Long getExecTimeoutCount() {
        return execTimeoutCount;
    }

    public void setExecTimeoutCount(Long execTimeoutCount) {
        this.execTimeoutCount = execTimeoutCount;
    }

    public Long getTotalExecTime() {
        return totalExecTime;
    }

    public void setTotalExecTime(Long totalExecTime) {
        this.totalExecTime = totalExecTime;
    }

    public Integer getAvgExecTime() {
        return avgExecTime;
    }

    public void setAvgExecTime(Integer avgExecTime) {
        this.avgExecTime = avgExecTime;
    }

    public Long getTotalWaitTime() {
        return totalWaitTime;
    }

    public void setTotalWaitTime(Long totalWaitTime) {
        this.totalWaitTime = totalWaitTime;
    }

    public Integer getAvgWaitTime() {
        return avgWaitTime;
    }

    public void setAvgWaitTime(Integer avgWaitTime) {
        this.avgWaitTime = avgWaitTime;
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

    public Long getCompletedTaskCount() {
        return completedTaskCount;
    }

    public void setCompletedTaskCount(Long completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }

    public Integer getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(Integer activeCount) {
        this.activeCount = activeCount;
    }

    public Long getWaitTimeout() {
        return waitTimeout;
    }

    public void setWaitTimeout(Long waitTimeout) {
        this.waitTimeout = waitTimeout;
    }

    public Long getExecTimeout() {
        return execTimeout;
    }

    public void setExecTimeout(Long execTimeout) {
        this.execTimeout = execTimeout;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
