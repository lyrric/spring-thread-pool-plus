package io.github.lyrric.test;

import java.util.Random;

public class MyTask implements Runnable{

    private final long sleepTime;

    public MyTask() {
        this.sleepTime = (long) (new Random().nextDouble() * 1000*60);
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
