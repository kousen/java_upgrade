package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("Convert2MethodRef")
public class LazyErrorMessage {
    private final Logger logger = Logger.getLogger(LazyErrorMessage.class.getName());

    private String getErrorMessage() {
        System.out.println("Calculating error message...");
        return "This is the error message";
    }

    @Test
    void eagerErrorMessage() {
        boolean x = true;
        logger.fine(getErrorMessage());
        assertTrue(x, getErrorMessage());  // eagerly creates the error message
    }

    @Test
    void lazyErrorMessage() {
        boolean x = true;
        logger.fine(() -> getErrorMessage());  // Supplier<String>
        assertTrue(x, () -> getErrorMessage()); // does NOT create the error message
    }
}
