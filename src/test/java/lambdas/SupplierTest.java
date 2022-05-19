package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("Convert2MethodRef")
public class SupplierTest {
    private final Logger logger = Logger.getLogger(SupplierTest.class.getName());

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "argument should be true";
    }

    @Test
    void lazyErrorMessageSupplier() {
        boolean x = true;
        // assertTrue(x, getErrorMessage());     // assertTrue(boolean, String)
        assertTrue(x, () -> getErrorMessage());  // assertTrue(boolean, Supplier<String>)
        assertTrue(x, this::getErrorMessage);
    }

    @Test
    void lazyLogger() {
        long start = System.currentTimeMillis();
        logger.fine(getErrorMessage());       // creates message even if not seen
        logger.fine(() -> getErrorMessage()); // creates message only if seen
        logger.fine(this::getErrorMessage);   // creates message only if seen
        long end = System.currentTimeMillis();
        System.out.println("Lazy logger took " + (end - start) + " ms");
    }
}
