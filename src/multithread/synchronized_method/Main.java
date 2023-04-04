package multithread.synchronized_method;

public class Main {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("Working in thread " + Thread.currentThread().getName());
            synchronizeMethod();
            System.out.println("Thread has done its task " + Thread.currentThread().getName());
        };
        Thread thread1 = new Thread(runnable, "Thread with runnable 1");
        Thread thread2 = new Thread(runnable, "Thread with runnable 2");
        thread1.start();
        thread2.start();
    }

    private synchronized static void synchronizeMethod() {
        try {
            System.out.println("Synchronized method working with " + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("Synchronized method working with " + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
