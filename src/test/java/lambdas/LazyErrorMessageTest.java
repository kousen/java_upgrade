package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("Convert2MethodRef")
public class LazyErrorMessageTest {
    private final Logger logger = Logger.getLogger(LazyErrorMessageTest.class.getName());

    private String getErrorMessage() {
        System.out.println("Calculating error message...");
        return "This is the error message";
    }

    private String getLogMessage() {
        System.out.println("Calculating log message...");
        return "This is the log message";
    }

    @Test
    void eagerErrorMessage() {
        boolean x = true;
        logger.fine(getLogMessage());
        assertTrue(x, getErrorMessage());  // eagerly creates the error message
    }

    @Test
    void lazyErrorMessage() {
        boolean x = true;
        logger.fine(() -> getLogMessage());  // Supplier<String>
        assertTrue(x, () -> getErrorMessage()); // does NOT create the error message
    }
}
