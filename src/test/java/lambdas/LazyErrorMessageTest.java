package lambdas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorMessageTest {

    private String getErrorMessage() {
        System.out.println("Generating error message...");
        return "x should be true";
    }

    @Test
    void assertArgIsTrue() {
        boolean x = true;
        assertTrue(x, getErrorMessage()); // 2nd arg is a String
    }

    @Test
    void assertArgIsTrue_lazyErrorMessage() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());  // 2nd arg is a Supplier<String>
    }
}
