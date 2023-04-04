package multithread.cafe;

public class Clients implements Runnable {
    @Override
    public void run() {

        try {
            System.out.println("Clients from thread :" + Thread.currentThread().getName() + " looking for table");
            Main.tables.acquire();
            System.out.println("Clients from thread :" + Thread.currentThread().getName() + " asked for waiter");
            Main.waiter.acquire();
            System.out.println("Waiter came to clients from thread :" + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("Waiter received order from clients from thread  :" + Thread.currentThread().getName());
            Main.waiter.release();
            Thread.sleep(1000);
            Main.waiter.acquire();
            System.out.println("Waiter brought order for clients from thread  :" + Thread.currentThread().getName());
            Main.waiter.release();
            Thread.sleep(1000);
            System.err.println("Clients go away from thread  :" + Thread.currentThread().getName());
            Main.tables.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
