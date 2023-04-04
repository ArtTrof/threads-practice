package multithread.cafe;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {
    static final Semaphore tables = new Semaphore(3);
    static final Semaphore waiter = new Semaphore(1);
   public  static boolean isAvailableHours=true;
   public static synchronized boolean isOpen(){
       return isAvailableHours;
   }
   public static synchronized void closed(){
       isAvailableHours = false;
   }
    public static void main(String[] args) throws InterruptedException {
       Runnable cafe = ()->{
           int i = 0;
           while (isOpen()){
               try{
                   new Thread(new Clients(),String.valueOf(i)).start();
                   i++;
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
       };
       Thread cafeThread =  new Thread(cafe,"Cafe");
       cafeThread.start();
       TimeUnit.SECONDS.sleep(5);
       closed();

    }
}
