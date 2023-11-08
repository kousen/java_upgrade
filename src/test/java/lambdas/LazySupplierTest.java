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
        return "Log message";
    }

    @Test
    void eagerErrorMessage() {
        boolean x = true;
        logger.fine(getLogMessage());
        assertTrue(x, getErrorMessage());
    }

    @Test
    void lazyErrorMessage() {
        boolean x = true;
        logger.fine(() -> getLogMessage());
        assertTrue(x, () -> getErrorMessage());
    }
}
