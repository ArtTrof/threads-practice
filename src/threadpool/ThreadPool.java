package threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private BlockingQueue blockingQueue;
    private List<ThreadPoolRunnable> runables = new ArrayList<>();
    private boolean isStopped = false;

    public ThreadPool(int numOfThreads, int maxOfTasks) {
        blockingQueue = new ArrayBlockingQueue(maxOfTasks);
        for (int i = 0; i < numOfThreads; i++) {
            ThreadPoolRunnable threadPoolRunnable = new ThreadPoolRunnable(blockingQueue);
            runables.add(threadPoolRunnable);
        }
        for (ThreadPoolRunnable runnable : runables) {
            new Thread(runnable).start();
        }
    }

    public synchronized void execute(Runnable task) {
        if (this.isStopped)
            throw new IllegalStateException("Thread pool is stopped");
        this.blockingQueue.add(task);
    }
    public synchronized void stop(){
        this.isStopped=true;
        for (ThreadPoolRunnable threadPoolRunnable:runables){
            threadPoolRunnable.doStop();
        }
    }
    public synchronized void waitTillTasksFinished() throws InterruptedException {
        while (blockingQueue.size()>0){
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
