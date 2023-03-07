package io.github.lyrric.task;

public class RunnableMonitorDecorator extends AbstractTaskMonitorDecorator implements Runnable{
    private final Runnable task;
    public RunnableMonitorDecorator(Runnable runnable) {
        super();
        task = runnable;
    }

    @Override
    public void run() {
        beforeExec();
        try {
            task.run();
        }finally {
            afterExec();
        }
    }


}
