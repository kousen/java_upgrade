package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorMessageTest {
    private final Logger logger = Logger.getLogger(LazyErrorMessageTest.class.getName());

    private String getLogMessage() {
        System.out.println("Generating log message...");
        return "x should be true";
    }

    private String getErrorMessage() {
        System.out.println("Generating error message...");
        return "x should be true";
    }

    @Test
    void assertArgIsTrue() {
        boolean x = true;
        assertTrue(x, getErrorMessage()); // 2nd arg is a String
        logger.fine(getLogMessage());
    }

    @Test
    void assertArgIsTrue_lazyErrorMessage() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());  // 2nd arg is a Supplier<String>
        assertTrue(x, this::getErrorMessage);  // 2nd arg is a Supplier<String>
        logger.fine(() -> getLogMessage());
    }
}
