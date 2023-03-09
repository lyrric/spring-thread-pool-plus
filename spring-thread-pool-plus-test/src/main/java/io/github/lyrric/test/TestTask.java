package io.github.lyrric.test;

import java.util.Random;

public class TestTask implements Runnable{

    private final long sleepTime;

    public TestTask() {
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
