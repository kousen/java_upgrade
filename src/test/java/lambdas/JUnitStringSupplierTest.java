package lambdas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnitStringSupplierTest {

    private String getError() {
        System.out.println("Inside getError()");
        return "x should be true";
    }

    @Test
    void showErrorMessage() {
        boolean x = true;
        // assertTrue(x, getError());
        assertTrue(x, () -> getError());  // Supplier<String>
    }
}
