package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazySupplierTest {
    private final Logger logger = Logger.getLogger(LazySupplierTest.class.getName());

    private String getErrorMessage() {
        System.out.println("getErrorMessage() called");
        return "Error message";
    }

    private String getLogMessage() {
        System.out.println("getLogMessage() called");
        return "Test passed";
    }

    @Test
    void eagerSupplier() {
        boolean x = true;
        assertTrue(x, getErrorMessage());
        logger.fine(getLogMessage());
    }

    @Test
    void lazySupplier() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());
        logger.fine(() -> getLogMessage());
    }
}
