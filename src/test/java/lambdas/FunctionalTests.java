package lambdas;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionalTests {
    private final Logger logger = Logger.getLogger(FunctionalTests.class.getName());

    @Test
    void iterableWithConsumer() {
        Consumer<String> consolePrint = System.out::println;
        Consumer<String> consoleLogger = logger::info;
        // function composition
        Consumer<String> consumer = consolePrint.andThen(consoleLogger);

        List.of("this", "is", "a", "list", "of", "strings")
                //.forEach(System.out::println);
                .forEach(consumer);
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
