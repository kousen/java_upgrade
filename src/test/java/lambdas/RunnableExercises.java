package lambdas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class RunnableExercises {
    private ExecutorService executorService;

    @BeforeEach
    public void setUp() {
        executorService = Executors.newFixedThreadPool(4);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

    @Test
    public void implementRunnableAsAnonymousInnerClass() throws InterruptedException {
        // TODO: Submit a Runnable using an anonymous inner class
        // The run method should set the result to "Anonymous Inner Class"
        
        AtomicReference<String> result = new AtomicReference<>("");
        CountDownLatch latch = new CountDownLatch(1);
        
        // executorService.submit(new Runnable() {
        //     @Override
        //     public void run() {
        //         // Set result and count down latch
        //     }
        // });
        
        // assertTrue(latch.await(1, TimeUnit.SECONDS));
        // assertEquals("Anonymous Inner Class", result.get());
    }

    @Test
    public void implementRunnableAsExpressionLambda() throws InterruptedException {
        // TODO: Submit the same Runnable using an expression lambda
        
        AtomicReference<String> result = new AtomicReference<>("");
        CountDownLatch latch = new CountDownLatch(1);
        
        // executorService.submit(() -> ...);
        
        // assertTrue(latch.await(1, TimeUnit.SECONDS));
        // assertEquals("Expression Lambda", result.get());
    }

    @Test
    public void implementRunnableAsBlockLambda() throws InterruptedException {
        // TODO: Submit a Runnable using a block lambda that:
        // 1. Stores the current thread name
        // 2. Sets the result to "Block Lambda"
        
        AtomicReference<String> result = new AtomicReference<>("");
        AtomicReference<String> threadName = new AtomicReference<>("");
        CountDownLatch latch = new CountDownLatch(1);
        
        // executorService.submit(() -> {
        //     // Store thread name
        //     // Set result
        //     // Count down latch
        // });
        
        // assertTrue(latch.await(1, TimeUnit.SECONDS));
        // assertEquals("Block Lambda", result.get());
        // assertTrue(threadName.get().startsWith("pool-"));
    }

    @Test
    public void assignLambdaToVariable() throws InterruptedException {
        // TODO: Create a lambda, assign it to a Runnable variable, then submit it
        
        AtomicReference<String> result = new AtomicReference<>("");
        CountDownLatch latch = new CountDownLatch(1);
        
        // Runnable task = ...
        // executorService.submit(task);
        
        // assertTrue(latch.await(1, TimeUnit.SECONDS));
        // assertEquals("Assigned to Variable", result.get());
    }

    @Test
    public void useMethodReference() throws InterruptedException {
        // TODO: Submit a Runnable using a method reference
        
        AtomicReference<String> result = new AtomicReference<>("");
        CountDownLatch latch = new CountDownLatch(1);
        
        // Create an instance method that can be used as a method reference
        Runnable methodRefExample = new Runnable() {
            @Override
            public void run() {
                result.set("Method Reference");
                latch.countDown();
            }
        };
        
        // TODO: Submit using method reference instead of lambda
        // executorService.submit(methodRefExample::run);
        
        // assertTrue(latch.await(1, TimeUnit.SECONDS));
        // assertEquals("Method Reference", result.get());
    }

    @Test
    public void submitMultipleTasks() throws InterruptedException {
        // TODO: Submit multiple Runnables and wait for all to complete
        
        CountDownLatch latch = new CountDownLatch(3);
        StringBuilder result = new StringBuilder();
        
        // TODO: Submit three tasks that append "First", "Second", "Third" to result
        // Note: Use synchronization since StringBuilder isn't thread-safe
        
        // executorService.submit(() -> {
        //     synchronized (result) {
        //         result.append("First");
        //     }
        //     latch.countDown();
        // });
        
        // assertTrue(latch.await(1, TimeUnit.SECONDS));
        // String finalResult = result.toString();
        // assertTrue(finalResult.contains("First"));
        // assertTrue(finalResult.contains("Second"));
        // assertTrue(finalResult.contains("Third"));
    }

    @Test
    public void virtualThreadsDemo() throws InterruptedException {
        // TODO: If using Java 21+, create an executor with virtual threads
        // Otherwise, skip this test
        
        // Skip if not Java 21+
        String javaVersion = System.getProperty("java.version");
        if (!javaVersion.startsWith("21") && !javaVersion.startsWith("22") && !javaVersion.startsWith("23")) {
            return;
        }
        
        AtomicReference<String> result = new AtomicReference<>("");
        CountDownLatch latch = new CountDownLatch(1);
        
        // TODO: Create virtual thread executor
        // try (ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
        //     virtualExecutor.submit(() -> {
        //         result.set("Virtual Thread: " + Thread.currentThread());
        //         latch.countDown();
        //     });
        // }
        
        // assertTrue(latch.await(1, TimeUnit.SECONDS));
        // assertTrue(result.get().contains("Virtual"));
    }
}