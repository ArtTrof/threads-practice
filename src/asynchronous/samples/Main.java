package asynchronous.samples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//sample for Callable and Future
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Main process started");

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + " started work");
            Thread.sleep(1000);
            return Thread.currentThread().getName();
        };

        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            futures.add(executorService.submit(callable));
        }
        System.out.println("Main is still working");

        for (Future<String> future : futures) {
            System.err.println("Thread result : " + future.get());
        }
        executorService.shutdown();
    }
}
