package io.github.lyrric.task;

import org.springframework.util.StopWatch;

public abstract class AbstractTaskMonitorDecorator {

    private final StopWatch waitWatch = new StopWatch();
    private final StopWatch execWatch = new StopWatch();

    public AbstractTaskMonitorDecorator() {
        waitWatch.start();
    }

    protected void beforeExec(){
        waitWatch.stop();
        execWatch.start();
    }

    protected void afterExec(){
        execWatch.stop();
    }

    public long getWaitTime() {
        return waitWatch.getTotalTimeMillis();
    }

    public long getExecTime() {
        return execWatch.getTotalTimeMillis();
    }
}
