package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorMessageTest {
    private final Logger log = Logger.getLogger(getClass().getName());

    private String getLogMessage() {
        System.out.println("Generating log message...");
        return "log message";
    }

    private String getErrorMessage() {
        System.out.println("Generating error message...");
        return "x should be true";
    }

    @Test
    void assertArgIsTrue() {
        boolean x = true;
        log.fine(getLogMessage());
        assertTrue(x, getErrorMessage()); // 2nd arg is a String
    }

    @Test
    void assertArgIsTrue_lazyErrorMessage() {
        boolean x = true;
        // log.fine(() -> getLogMessage());
        log.fine(this::getLogMessage);
        // assertTrue(x, () -> getErrorMessage());  // 2nd arg is a Supplier<String>
        assertTrue(x, this::getErrorMessage);  // 2nd arg is a Supplier<String>
    }
}
