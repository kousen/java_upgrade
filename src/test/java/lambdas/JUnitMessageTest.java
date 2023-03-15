package lambdas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnitMessageTest {

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage()");
        return "Error message";
    }

    @Test
    void add() {
        boolean x = true;
        assertTrue(x, getErrorMessage());
    }

    @Test
    void addWithSupplier() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());
    }
}
