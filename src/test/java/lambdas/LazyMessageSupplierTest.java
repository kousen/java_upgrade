package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyMessageSupplierTest {
    private final Logger logger = Logger.getLogger(LazyMessageSupplierTest.class.getName());

    private String getErrorMessage() {
        System.out.println("Generating error message...");
        return "An error occurred";
    }

    private String getLogMessage() {
        System.out.println("Generating log message...");
        return "A log message";
    }

    @Test
    void testBoolean() {
        boolean x = true;
        logger.fine(getLogMessage());
        assertTrue(x, getErrorMessage());
    }

    @Test
    void testBooleanWithLazyErrorMessage() {
        boolean x = true;
        logger.fine(() -> getLogMessage());
        assertTrue(x, () -> getErrorMessage());
    }
}
