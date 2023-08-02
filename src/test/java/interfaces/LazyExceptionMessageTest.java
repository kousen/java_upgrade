package interfaces;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyExceptionMessageTest {

    private String getErrorMessage() {
        System.out.println("getErrorMessage() called");
        return "x should be true";
    }

    @Test
    void defaultAssertion() {
        boolean x = true;
        assertTrue(x, getErrorMessage());
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void lazyAssertion() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());
    }

    @Test @Disabled("fails half the time")
    void booleanSupplier_lazyAssertion() {
        Random random = new Random();
        assertTrue(random::nextBoolean, this::getErrorMessage);
    }
}
