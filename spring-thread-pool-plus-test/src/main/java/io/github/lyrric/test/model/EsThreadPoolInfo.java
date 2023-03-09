package io.github.lyrric.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class EsThreadPoolInfo {
    /** 线程池唯一标识 */
    private String key;
    /** 线程池名称 */
    private String name;
    /** 任务队列是否长度超过阈值 */
    private Boolean queueFullWarning ;
    /** 任务队列使用数量 */
    private Integer queueUseSize ;

    /** 任务队列使用比例阈值 */
    private Double queueWarningRatio;
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
    private long waitTimeout;
    /** 任务执行时间阈值，单位毫秒 */
    private long execTimeout;

    private Long createTime;

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

    public Integer getQueueTotalSize() {
        return queueTotalSize;
    }

    public void setQueueTotalSize(Integer queueTotalSize) {
        this.queueTotalSize = queueTotalSize;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
