package asynchronous.samples;

import java.util.concurrent.*;

public class FutureGetWithParams {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("Main process started");

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + " started work");
            Thread.sleep(4_000);
            return Thread.currentThread().getName();
        };
        Future<String> future = executorService.submit(callable);

        System.out.println("Main is still working");

        System.out.println("res: "+future.get(5,TimeUnit.SECONDS));

        executorService.shutdown();
    }
}
