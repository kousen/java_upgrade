package lambdas;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionalTests {
    @Test
    void iterableWithConsumer() {
        List.of("this", "is", "a", "list", "of", "strings")
                .forEach(System.out::println);
    }

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "error message";
    }

    @Test
    void assertTrueTest() {
        boolean x = true;
        assertTrue(x, () -> getErrorMessage());
        assertTrue(x, getErrorMessage());
    }

    @Test
    void iterateOverMap() {
        Map.ofEntries(
                        Map.entry("a", 1),
                        Map.entry("b", 2),
                        Map.entry("c", 2))
                .forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
