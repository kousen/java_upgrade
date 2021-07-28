package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnit5Test {
    private final Logger logger = Logger.getLogger(JUnit5Test.class.getName());

    private String createErrorMessage() {
        System.out.println("Inside createErrorMessage");
        return "false is not true";
    }

    private String createLoggerMessage() {
        System.out.println("Inside createLoggerMessage");
        return "here is my message";
    }

    @Test
    void failingTestWithMessage() {
        assertTrue(true, () -> createErrorMessage());
    }

    @Test
    void showInfoLogging() {
        logger.fine(() -> createLoggerMessage());
    }
}
