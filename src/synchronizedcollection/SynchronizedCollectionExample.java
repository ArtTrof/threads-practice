package synchronizedcollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SynchronizedCollectionExample {
    public static void main(String[] args) throws InterruptedException {
//        List<Integer> arrayList = new ArrayList<>();
            List<Integer> arrayList = Collections.synchronizedList(new ArrayList<>());
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        Runnable runnable = () -> {
            synchronized (arrayList){
            Iterator<Integer> iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(iterator.next());
            }
            }
        };

        Runnable runnable1 = () -> {
            for (int i = 4; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                arrayList.add(i);
                System.err.println(arrayList);
            }
        };
        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable1);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(arrayList);
    }
}
