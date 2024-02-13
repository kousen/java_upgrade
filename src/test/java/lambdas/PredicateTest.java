package lambdas;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PredicateTest {

    @Test
    void implementPredicateAsAnonInnerClass() {
        Predicate<String> p = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 5;
            }
        };
        assertFalse(p.test("Hello"));
        assertTrue(p.test("Hello World"));
    }

    @Test
    void implementPredicateAsExpressionLambda() {
        Predicate<String> p = s -> s.length() > 5;
        assertFalse(p.test("Hello"));
        assertTrue(p.test("Hello World"));
    }

    @Test
    void implementPredicateAsBlockLambda() {
        Predicate<String> p = s -> {
            System.out.println("Evaluating whether s length > 5: " + s);
            return s.length() > 5;
        };
        assertFalse(p.test("Hello"));
        assertTrue(p.test("Hello World"));
    }

    @Test
    void testRunnable() {
        Runnable r = () -> System.out.println("Hello World");
        Thread thread = new Thread(r);
        thread.start();
    }
}
