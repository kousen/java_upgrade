package lambdas;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FunctionalInterfacesTest {

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    @Test
    public void implementConsumerUsingAnonInnerClass() throws Exception {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("Hello, World!");
    }

    @Test
    public void implementConsumerUsingLambda() throws Exception {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("Hello, World!");
    }

    @Test
    public void implementConsumerUsingMethodReference() throws Exception {
        Consumer<String> consumer = System.out::println;
        consumer.accept("Hello, World!");
    }

    @Test
    public void implementSupplierUsingAnonInnerClass() throws Exception {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "Hello";
            }
        };
        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingLambda() throws Exception {
        Supplier<String> supplier = () -> "Hello";
        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingMethodReference() throws Exception {
        // Create a Supplier<Double> that calls Math.random()
        Supplier<Double> supplier = Math::random;
        assertTrue(supplier.get() >= 0.0);
        assertTrue(supplier.get() <= 1.0);

        // Create a DoubleSupplier that does the same
        DoubleSupplier doubleSupplier = Math::random;
        assertTrue(doubleSupplier.getAsDouble() >= 0.0);
        assertTrue(doubleSupplier.getAsDouble() <= 1.0);
    }

    @Test
    public void constructorReference() throws Exception {
        List<String> stringList = Arrays.asList("a b c b c d".split(" "));
        // stringList = List.of("a", "b", "c", "b", "c", "d"); // Java 10+ only
        assertEquals(6, stringList.size());

        // Add the strings to a Set
        // Set<String> strings = new HashSet<>(stringList);
        Set<String> strings = stringList.stream()
                .collect(Collectors.toSet());
        assertEquals(4, strings.size());
        assertEquals(HashSet.class, strings.getClass());

        // Add the strings to a TreeSet
        // SortedSet<String> sortedStrings = new TreeSet<>(stringList);
        SortedSet<String> sortedStrings = stringList.stream()
                .collect(Collectors.toCollection(TreeSet::new));
        assertEquals(4, sortedStrings.size());
        assertEquals(TreeSet.class, sortedStrings.getClass());
        assertEquals("a", sortedStrings.first());

    }

    @Test
    public void filterWithPredicate() throws Exception {
        IntStream.of(3, 1, 4, 1, 5, 9)
                .filter(n -> n % 2 == 0)  // accept even nums only
                .forEach(n -> assertTrue(n % 2 == 0));
    }
}
