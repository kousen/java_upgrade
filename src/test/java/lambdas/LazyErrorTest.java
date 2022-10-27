package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorTest {
    private final Logger logger = Logger.getLogger(LazyErrorTest.class.getName());

    private String getErrorMessage() {
        System.out.println("getErrorMessage() called");
        return "Error message";
    }

    @Test
    void assertTrueWithError() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());  // 2nd arg is a Supplier<String>
    }

    @Test
    void loggerWithLazyMessage() {
        logger.fine(getErrorMessage());
        logger.fine(() -> getErrorMessage());
        logger.fine(this::getErrorMessage);
    }
}
