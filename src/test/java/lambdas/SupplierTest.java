package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupplierTest {
    private final Logger logger = Logger.getLogger(SupplierTest.class.getName());

    private String getErrorMessage() {
        System.out.println("getErrorMessage()");
        return "error message";
    }

    @Test
    void lazyErrorMessageWithSupplier() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());
        logger.fine(() -> "x = " + x);
    }
}
