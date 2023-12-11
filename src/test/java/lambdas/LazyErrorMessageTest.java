package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("Convert2MethodRef")
public class LazyErrorMessageTest {
    private final Logger logger = Logger.getLogger(LazyErrorMessageTest.class.getName());

    private String getLogMessage() {
        System.out.println("Generating log message...");
        return "logging message for x";
    }

    private String getErrorMessage() {
        System.out.println("Generating error message...");
        return "x should be true";
    }

    @Test
    void assertArgIsTrue_eager() {
        boolean x = true;
        assertTrue(x, getErrorMessage()); // 2nd arg is a String
        logger.fine(getLogMessage());     // 2nd arg is a String
    }

    @Test
    void assertArgIsTrue_lazyErrorMessage() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());  // 2nd arg is a Supplier<String>
        logger.fine(() -> getLogMessage());      // 2nd arg is a Supplier<String>
    }
}
