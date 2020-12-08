package streams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitLazySupplier {
    @Test
    void retrieveErrorMessageLazily() {
        assertEquals(4, 2 + 2, getErrorMessage());        // Eager
        assertEquals(4, 2 + 2, () -> getErrorMessage());  // Lazy
    }


    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "error message";
    }
}
