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
        AtomicReference<String> result = new AtomicReference<>("");
        CountDownLatch latch = new CountDownLatch(1);
        
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                result.set("Anonymous Inner Class");
                latch.countDown();
            }
        });
        
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertEquals("Anonymous Inner Class", result.get());
    }

    @Test
    public void implementRunnableAsExpressionLambda() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        CountDownLatch latch = new CountDownLatch(1);
        
        executorService.submit(() -> {
            result.set("Expression Lambda");
            latch.countDown();
        });
        
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertEquals("Expression Lambda", result.get());
    }

    @Test
    public void implementRunnableAsBlockLambda() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        AtomicReference<String> threadName = new AtomicReference<>("");
        CountDownLatch latch = new CountDownLatch(1);
        
        executorService.submit(() -> {
            threadName.set(Thread.currentThread().getName());
            result.set("Block Lambda");
            latch.countDown();
        });
        
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertEquals("Block Lambda", result.get());
        assertTrue(threadName.get().startsWith("pool-"));
    }

    @Test
    public void assignLambdaToVariable() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        CountDownLatch latch = new CountDownLatch(1);
        
        Runnable task = () -> {
            result.set("Assigned to Variable");
            latch.countDown();
        };
        executorService.submit(task);
        
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertEquals("Assigned to Variable", result.get());
    }

    @Test
    public void useMethodReference() throws InterruptedException {
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
        
        executorService.submit(methodRefExample::run);
        
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertEquals("Method Reference", result.get());
    }

    @Test
    public void submitMultipleTasks() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        StringBuilder result = new StringBuilder();
        
        executorService.submit(() -> {
            synchronized (result) {
                result.append("First");
            }
            latch.countDown();
        });
        
        executorService.submit(() -> {
            synchronized (result) {
                result.append("Second");
            }
            latch.countDown();
        });
        
        executorService.submit(() -> {
            synchronized (result) {
                result.append("Third");
            }
            latch.countDown();
        });
        
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        String finalResult = result.toString();
        assertTrue(finalResult.contains("First"));
        assertTrue(finalResult.contains("Second"));
        assertTrue(finalResult.contains("Third"));
    }

    @Test
    public void virtualThreadsDemo() throws InterruptedException {
        // Skip if not Java 21+
        String javaVersion = System.getProperty("java.version");
        if (!javaVersion.startsWith("21") && !javaVersion.startsWith("22") && !javaVersion.startsWith("23")) {
            return;
        }
        
        AtomicReference<String> result = new AtomicReference<>("");
        CountDownLatch latch = new CountDownLatch(1);
        
        try (ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            virtualExecutor.submit(() -> {
                result.set("Virtual Thread: " + Thread.currentThread());
                latch.countDown();
            });
        }
        
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertTrue(result.get().contains("Virtual"));
    }
}