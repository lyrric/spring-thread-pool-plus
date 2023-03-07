package io.github.lyrric.task;

public class MyTask implements Runnable{

    private final long sleepTime;

    public MyTask(long sleepTime) {
        this.sleepTime = sleepTime;
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
