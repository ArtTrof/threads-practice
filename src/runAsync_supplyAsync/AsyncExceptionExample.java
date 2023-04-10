package runAsync_supplyAsync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncExceptionExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<Boolean> completableFuture = CompletableFuture.supplyAsync(() -> {
//            int[] array = new int[]{1, 2, 3, 4, 5};
//            for (int i = 0; i < 7; i++) {
//                System.out.println(array[i]);
//            }
//            return true;
//        }).exceptionally(throwable -> {
//            System.out.println("Exception:" + throwable.getMessage());
//            System.err.println("check input data");
//            return false;
//        });
//        System.out.println(completableFuture.get());
        CompletableFuture<Boolean> completableFuture = CompletableFuture.supplyAsync(() -> {
            int[] array = new int[]{1, 2, 3, 4, 5};
            for (int i = 0; i < 7; i++) {
                System.out.println(array[i]);
            }
            return true;
        }).handle((result, throwable) -> {
            if (throwable != null) {
                System.out.println("Exception:" + throwable.getMessage());
                System.err.println("check input data");
                return false;
            }
            return result;
        });
        System.out.println(completableFuture.get());
    }
}
