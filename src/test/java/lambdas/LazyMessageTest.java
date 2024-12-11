package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyMessageTest {
    private final Logger logger = Logger.getLogger(LazyMessageTest.class.getName());

    private String getErrorMessage() {
        System.out.println("Getting error message");
        return "Error message";
    }

    private String getLogMessage() {
        System.out.println("Getting log message");
        return "Log message";
    }

    @Test
    void eagerMessage() {
        boolean x = true;
        assertTrue(x, getErrorMessage());
        logger.fine(getLogMessage());
    }

    @Test
    void lazyMessage() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());
        logger.fine(() -> getLogMessage());
    }
}
