package lambdas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("Convert2MethodRef")
public class LazyMessageSupplierTest {

    private String getErrorMessage() {
        System.out.println("inside getErrorMessage()");
        return "Error message";
    }

    @Test
    void assertArgumentIsTrue() {
        boolean x = true;
        // assertTrue(Boolean, String) --> eagerly evaluates the String argument
        assertTrue(x, getErrorMessage());

        // assertTrue(Boolean, Supplier<String>) --> lazily evaluates the String argument
        assertTrue(x, () -> getErrorMessage());
    }
}
