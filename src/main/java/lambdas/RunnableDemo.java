package lambdas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableDemo {
    @SuppressWarnings("Convert2Lambda")
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Java 7 or earlier
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside an anonymous inner class");
            }
        });

        // Expression lambda
        executorService.submit(() -> System.out.println("Inside expression lambda"));

        // Block lambda
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Inside block lambda");
        });

        Runnable runnable = () -> System.out.println("Assigned to a variable");
        executorService.submit(runnable);

        executorService.shutdown();

    }
}

