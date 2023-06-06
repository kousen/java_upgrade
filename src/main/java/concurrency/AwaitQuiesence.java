package concurrency;

import java.util.concurrent.CompletableFuture;

public class AwaitQuiesence {
    private String sleepThenReturnString() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        return "42";
    }

    public CompletableFuture<Void> supplyThenAccept() {
        return CompletableFuture.supplyAsync(this::sleepThenReturnString)
                .thenApply(Integer::parseInt)
                .thenApply(x -> 2 * x)
                .thenAccept(System.out::println);
    }
}
