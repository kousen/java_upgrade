package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorMessageTest {
    private final Logger logger = Logger.getLogger(LazyErrorMessageTest.class.getName());

    private String getErrorMessage() {
        System.out.println("Generating error message...");
        return "x should be true";
    }

    private String getLogMessage() {
        System.out.println("Generating log message...");
        return "log message";
    }

    @Test // Eager version
    void assertArgIsTrue() {
        boolean x = true;
        assertTrue(x, getErrorMessage()); // 2nd arg is a String
        logger.fine(getLogMessage());
    }

    @Test // Lazy version
    void assertArgIsTrue_lazyErrorMessage() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());  // 2nd arg is a Supplier<String>
        logger.fine(() -> getLogMessage());
    }
}
