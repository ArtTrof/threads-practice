package asynchronous.samples;

import java.util.concurrent.*;

public class FutureIsCancelled {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Main process started");

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + " started work");
            Thread.sleep(2_000);
            return Thread.currentThread().getName();
        };
        Future<String> future = executorService.submit(callable);

        System.out.println("Main is still working");
        TimeUnit.SECONDS.sleep(3);

        if (!future.isDone()) {
            future.cancel(true);
        }

        if (future.isCancelled()) {
            System.out.println("Task was cancelled");
        } else {
            System.out.println("result: " + future.get());
        }

        executorService.shutdown();
    }
}
