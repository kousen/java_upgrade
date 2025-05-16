package concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class CompletableFutureDemosTest {
    private final CompletableFutureDemos demo = new CompletableFutureDemos();

    @Test
    public void testRemote() throws Exception {
        Product product = demo.getProduct(1).get();
        assertEquals(1, product.getId());
    }

    @Test
    public void testLocal() throws Exception {
        demo.getProduct(1).get();
        Product product = demo.getProduct(1).get();
        assertEquals(1, product.getId());
    }

    @Test
    public void testException() throws Exception {
        assertThrows(ExecutionException.class, () -> {
            demo.getProduct(666).get();
        });
    }

    @Test
    public void testExceptionWithCause() throws Exception {
        try {
            demo.getProduct(666).get();
            Assertions.fail("Houston, we have a problem...");
        } catch (ExecutionException e) {
            assertEquals(ExecutionException.class, e.getClass());
            assertEquals(RuntimeException.class, e.getCause().getClass());
        }
    }

    @Test
    public void getProductAsync() throws Exception {
        Product product = demo.getProductAsync(1).get();
        assertEquals(1, product.getId());
    }
}