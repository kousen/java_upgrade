package lambdas;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorMessageTest {
    private final Logger logger = Logger.getLogger(LazyErrorMessageTest.class.getName());

    private String getLogMessage() {
        System.out.println("getLogMessage() called");
        return "This is a log message";
    }


    private String getErrorMessage(boolean x) {
        System.out.println("getErrorMessage() called");
        return String.format("%s should be true", x);
    }

    @Test
    void testErrorMessage() {
        boolean x = new Random().nextBoolean();
        // assertTrue(x, getErrorMessage(x));
        logger.fine(this::getLogMessage);
        assertTrue(x, () -> getErrorMessage(x));
    }
}
