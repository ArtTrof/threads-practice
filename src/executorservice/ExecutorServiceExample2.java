package executorservice;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample2 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            int taskNumber = i;
            service.submit(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Task is interrupted");
                }
                System.out.println(Thread.currentThread().getName()+" task number :" + taskNumber);
            });
        }

//        System.out.println( service.shutdownNow().size());
        List<Runnable> runnables = service.shutdownNow();
        ExecutorService service1 = Executors.newFixedThreadPool(5);
        for (Runnable runnable:runnables){
             service1.submit(runnable);
        }
        service1.shutdown();
    }
}
