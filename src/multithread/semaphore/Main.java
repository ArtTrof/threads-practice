package multithread.semaphore;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        Runnable runnable = () -> {
            System.out.printf("Thread: %s requesting access to method.\n", Thread.currentThread().getName());
            try {
                semaphore.acquire();
                method();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " has ended working with method.");
            semaphore.release();
        };
        Thread thread1 = new Thread(runnable,"Thread 1");
        Thread thread2 = new Thread(runnable,"Thread 2");
        Thread thread3 = new Thread(runnable,"Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

    }
    private static void method(){
        System.out.println("Method working with" +Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
