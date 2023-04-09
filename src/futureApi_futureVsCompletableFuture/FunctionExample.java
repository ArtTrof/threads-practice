package futureApi_futureVsCompletableFuture;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionExample {
    public static void main(String[] args) {
        Consumer<String> helloConsumer = string -> {
            String hello = "hello " + string;
            System.out.println(hello);
        };
        helloConsumer.accept("guest");

        Function<Integer, String> integerStringFunction = integer -> "Value " + integer;
        System.out.println(integerStringFunction.apply(5));

        Supplier<String> supplier = () -> {
            Random random = new Random();
            return "Random value:" + random.nextFloat();
        };
        System.out.println(supplier.get());

        BiFunction<Consumer<String>, Supplier<String>, Boolean> biFunction = ((stringConsumer, stringSupplier) -> {
            String supplierString = stringSupplier.get();
            stringConsumer.accept(supplierString);
            return true;
        });
        System.out.println(biFunction.apply(helloConsumer, supplier));
    }
}
