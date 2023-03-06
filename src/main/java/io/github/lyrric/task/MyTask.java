package io.github.lyrric.task;

import io.github.lyrric.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTask implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private final long sleepTime;

    private final int num;
    public MyTask(long sleepTime, int num) {
        this.sleepTime = sleepTime;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
