package threadpool;

import java.util.concurrent.BlockingQueue;

public class ThreadPoolRunnable implements Runnable {
    private Thread thread;
    private BlockingQueue blockingQueue;
    private boolean isStopped = false;

    public ThreadPoolRunnable(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped){
            try {
                Runnable runnable = (Runnable) blockingQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        this.thread.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}
