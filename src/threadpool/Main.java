package threadpool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool =  new ThreadPool(2,10);
        for (int i = 0; i < 10; i++) {
            int taskNumber = i ;
            threadPool.execute(()->  {
                System.out.println(Thread.currentThread().getName()+" Task:"+taskNumber);
                    });
        }
        threadPool.waitTillTasksFinished();
        threadPool.stop();
    }
}
