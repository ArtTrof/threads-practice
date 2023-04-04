package asynchronous.samples;

import java.util.concurrent.*;

public class FutureIsDone {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Main process started");

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + " started work");
            Thread.sleep(10_000);
            return Thread.currentThread().getName();
        };
        Future<String> future = executorService.submit(callable);

        System.out.println("Main is still working");
        TimeUnit.SECONDS.sleep(3);

        while (!future.isDone()){
            System.out.println("Task is in process ...");
            TimeUnit.SECONDS.sleep(1);
        }

        executorService.shutdown();
    }
}
