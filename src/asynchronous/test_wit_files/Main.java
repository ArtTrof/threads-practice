package asynchronous.test_wit_files;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Callable<Void> callable1 = () -> {
            FileUtils.getStatistics("src/resources/file1.csv", "src/resources/result1.csv");
            return null;
        };
        Callable<Void> callable2 = () -> {
            FileUtils.getStatistics("src/resources/file2.csv", "src/resources/result2.csv");
            return null;
        };
        Callable<Void> callable3 = () -> {
            FileUtils.getStatistics("src/resources/file3.csv", "src/resources/result3.csv");
            return null;
        };
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        long start = System.currentTimeMillis();
        executorService.submit(callable1);
        executorService.submit(callable2);
        executorService.submit(callable3);

        executorService.shutdown();
        //without asynchr calls
//        FileUtils.getStatistics("src/resources/file1.csv","src/resources/result1.csv");
//        FileUtils.getStatistics("src/resources/file2.csv","src/resources/result2.csv");
//        FileUtils.getStatistics("src/resources/file3.csv","src/resources/result3.csv");


        System.out.println("time stats:" + (System.currentTimeMillis() - start));
    }
}
