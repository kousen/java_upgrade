package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorMessage {
    private final Logger log = Logger.getLogger("Logger");

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "False is not true";
    }

    @Test
    void showError() {
        assertTrue(true, () -> getErrorMessage());
        log.fine(() -> "Error messsage");  // Creates the log message only if it will be seen
    }
}
