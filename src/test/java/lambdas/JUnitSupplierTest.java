package lambdas;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnitSupplierTest {
    @Test
    void assertTrueWithoutErrorMessage() {
        boolean x = true;
        assertTrue(x);
    }

    @SuppressWarnings("ConstantConditions")
    @Test @Disabled("for demonstration purposes")
    void assertTrue_isFalse_WithoutErrorMessage() {
        boolean x = false;
        assertTrue(x);
    }

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage()");
        return "x should be true";
    }

    @Test
    void assertTrueWithErrorMessage() {
        boolean x = true;
        assertTrue(x, getErrorMessage());  // error method invoked even if not needed
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void assertTrueWithErrorMessageSupplier() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage()); // second arg is Supplier<String>
        // error method is not invoked at all (only invoke get() if error)
    }

    @Test
    void assertTrueWithErrorMessageSupplier_methodRef() {
        boolean x = true;
        assertTrue(x, this::getErrorMessage);
    }
}
