package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorMessageTest {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    private String getErrorMessage() {
        System.out.println("Generating error message...");
        return "x should be true";
    }

    private String getLogMessage() {
        System.out.println("Generating log message...");
        return "x should be true";
    }

    @Test
    void assertArgIsTrue() {
        boolean x = true;
        assertTrue(x, getErrorMessage()); // 2nd arg is a String
        log.fine(getLogMessage());
    }

    @Test
    void assertArgIsTrue_lazyErrorMessage() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());  // 2nd arg is a Supplier<String>
        log.fine(() -> getLogMessage());
    }
}
