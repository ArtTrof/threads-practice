package futureApi_futureVsCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> completableFuture = new CompletableFuture<>();
//        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("result");
        CompletableFuture<String> completableFuture = CompletableFuture.failedFuture(new RuntimeException());
        CompletionStage<String>completionStage =CompletableFuture.completedStage("result");

//
//        System.out.println(completableFuture.isDone());
//        System.out.println(completableFuture.get());
        System.out.println(completionStage.toCompletableFuture().isDone());
        System.out.println(completionStage.toCompletableFuture().get());

    }
}
