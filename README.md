# 动态线程池
- 实时监测线程池的各项指标信息
- 通过配置中心（如nacos）可以动态设置线程池参数
## 与Grafana结合可以实现如下图效果 
<img src="images/1.png" alt="任务完成情况"/> 
<img src="images/2.png" alt="线程状况" />
<img src="images/3.png" alt="任务队列情况" />
<img src="images/4.png" alt="平均等待和执行时间" />

## 实时监测线程池的状态
检测线程池的如下信息:
```
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
    /** 队列容量 */
    private Integer queueCapacity;


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
    /** 任务等待时长阈值，单位毫秒 */
    private Long waitTimeout;
    /** 任务执行时间阈值，单位毫秒 */
    private Long execTimeout;
    private Long createTime;
```


## 动态设置线程池参数
通过nacos动态设置线程池参数，可设置的线程池参数如下：
```
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
```

## 使用方法
## 待定