package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazySupplierTest {
    private final Logger logger = Logger.getLogger(LazySupplierTest.class.getName());

    private String getErrorMessage() {
        System.out.println("calling getErrorMessage()");
        return "An error occurred";
    }

    private String getLogMessage() {
        System.out.println("calling getLogMessage()");
        return "log message";
    }

    @Test
    void eagerMessages() {
        boolean condition = true;
        assertTrue(condition, getErrorMessage());
        logger.fine(getLogMessage());
    }

    @Test
    void lazyMessages() {
        boolean condition = true;
        assertTrue(condition, () -> getErrorMessage());
        logger.fine(() -> getLogMessage());
    }
}
