package io.github.lyrric.task;

import java.util.Random;

public class MyTask implements Runnable{

    private final long sleepTime;

    public MyTask() {
        this.sleepTime = (long) (new Random().nextDouble() * 10000);
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
