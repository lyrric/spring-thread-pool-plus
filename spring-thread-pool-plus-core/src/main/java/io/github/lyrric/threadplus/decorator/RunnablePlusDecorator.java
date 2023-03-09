package io.github.lyrric.threadplus.decorator;

public class RunnablePlusDecorator extends AbstractTaskPlusDecorator implements Runnable{
    private final Runnable task;
    public RunnablePlusDecorator(Runnable runnable) {
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
