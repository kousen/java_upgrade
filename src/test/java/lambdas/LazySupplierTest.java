package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazySupplierTest {
    private final Logger logger = Logger.getLogger(LazySupplierTest.class.getName());

    private String getErrorMessage() {
        System.out.println("getErrorMessage called");
        return "x should be true";
    }

    @Test
    void testWithEagerSupplier() {
        boolean x = true;
        assertTrue(x, getErrorMessage());
    }

    @Test
    void testWithLazySupplier() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());
    }

    private String getLogMessage() {
        System.out.println("getLogMessage called");
        return "This is my log message";
    }

    @Test
    void lazyLoggerTest() {
        logger.fine(getLogMessage());
        logger.fine(() -> getLogMessage());
    }
}
