package runAsync_supplyAsync;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class SupplyAsyncExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner in = new Scanner(System.in);
        int count = 1;
        while (true) {
            int tempCount = count;
            System.out.println("input file name");
            String filename = in.nextLine();
            Supplier<List<Integer>> readFile = () -> {
                File file = new File("src/resources/" + filename);
                try {
                    Scanner reader = new Scanner(file);
                    List<Integer> result = new ArrayList<>();
                    while (reader.hasNextLine()) {
                        result.add(Integer.valueOf(reader.nextLine()));
                    }
                    reader.close();
                    return result;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Exception reading the file");
                }
            };
            CompletableFuture<List<Integer>> asyncResult = CompletableFuture.supplyAsync(readFile);
            System.out.println("1 - find max number");
            System.out.println("2 - all elements sum");
            System.out.println("3 - average of all elements");
            System.out.println("input your choice");
            int choice = in.nextInt();
            if (!asyncResult.isDone()) {
                System.out.println("File:" + filename + " is being read");
            }
            switch (choice) {
                case 1:
                    asyncResult.thenAccept(result -> {
                        System.err.println("result:" + tempCount + ".File:" + filename);
                        System.err.println("Min number" + result.get(0));
                    });
                    break;
                case 2:
                    asyncResult.thenAccept(result -> {
                        Integer sum = 0;
                        System.err.println("result:" + tempCount + ".File:" + filename);
                        for (Integer integer : result) {
                            sum = sum + integer;
                        }
                        System.err.println("Sum is:" + sum);
                    });
                    break;
                case 3:
                    asyncResult.thenAccept(result -> {
                        Integer avg = 0;
                        System.err.println("result:" + tempCount + ".File:" + filename);
                        avg = result.get(9) / 2;
                        System.err.println("AVG is:" + avg);
                    });
                    break;
                default:
                    System.out.println("bad choice");
                    break;
            }
            count++;
            in.nextLine();
        }
    }
}
