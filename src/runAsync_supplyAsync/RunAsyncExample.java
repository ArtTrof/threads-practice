package runAsync_supplyAsync;

import java.util.concurrent.*;

public class RunAsyncExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("Processing in Thread:%s", Thread.currentThread().getName());
        };
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(runnable, executorService);
        System.out.println("Async task was created");

        completableFuture.get();
        executorService.shutdown();
    }
}
