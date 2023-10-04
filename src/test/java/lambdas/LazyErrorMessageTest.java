package lambdas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorMessageTest {

    private String getErrorMessage(boolean value) {
        System.out.println("Generating error message...");
        return value + " should be true";
    }

    @Test
    void assertArgIsTrue() {
        boolean x = true;
        assertTrue(x, getErrorMessage(x)); // 2nd arg is a String
    }

    @Test
    void assertArgIsTrue_lazyErrorMessage() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage(x));  // 2nd arg is a Supplier<String>
    }
}
