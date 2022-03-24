package streams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorTest {

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "x should be > 0";
    }

    @Test
    void showErrorMessage() {
        int x = 42;
        assertTrue(x > 0, () -> getErrorMessage());
    }
}
