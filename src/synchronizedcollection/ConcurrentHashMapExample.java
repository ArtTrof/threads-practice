package synchronizedcollection;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer,String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(1,"one");
        concurrentHashMap.put(2,"two");
        concurrentHashMap.put(3,"three");
        concurrentHashMap.put(4,"four");

        Runnable runnable1 = ()->{
            System.out.println(concurrentHashMap);
            Iterator<Integer>iterator = concurrentHashMap.keySet().iterator();
            while (iterator.hasNext()){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Integer i = iterator.next();
                System.out.println(i+":"+concurrentHashMap.get(i));
            }
        };

        Runnable runnable2 = ()->{
            System.err.println();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            concurrentHashMap.put(6,"six");
            System.err.println(concurrentHashMap);
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
