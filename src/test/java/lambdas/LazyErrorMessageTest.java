package lambdas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorMessageTest {
    @Test
    public void checkBoolean() {
        boolean x = false;
        assertTrue(x);
    }

    private String getErrorMessage() {
        System.out.println("getErrorMessage() called");
        return "should be true";
    }

    @Test
    public void checkBooleanWithErrorMessage() {
        boolean x = true;
        assertTrue(x, getErrorMessage());  // Eager evaluation of error
    }

    @Test
    public void checkBooleanWithLazyErrorMessage() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());  // Lazy evaluation of error
    }
}
