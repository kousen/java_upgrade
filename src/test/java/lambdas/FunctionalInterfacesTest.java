package lambdas;

import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class FunctionalInterfacesTest {

    @Test
    public void implementConsumerUsingAnonInnerClass() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("Hello, World!");
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void implementConsumerUsingLambda() {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("Hello, World!");
    }

    @Test
    public void implementConsumerUsingMethodReference() {
        Consumer<String> printer = System.out::println;
        printer.accept("Hello, World!");
    }

    @Test
    public void implementSupplierUsingAnonInnerClass() {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "Hello";
            }
        };

        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingLambda() {

//        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingMethodReference() {
        // Create a Supplier<Double> that calls Math.random()

//        assertTrue(supplier.get() >= 0.0);
//        assertTrue(supplier.get() <= 1.0);

        // Create a DoubleSupplier that does the same

//        assertTrue(doubleSupplier.getAsDouble() >= 0.0);
//        assertTrue(doubleSupplier.getAsDouble() <= 1.0);
    }

    @Test
    public void constructorReference() {
        List<String> stringList = Arrays.asList("a b c b c d".split(" "));

        assertEquals(6, stringList.size());

        // Add the strings to a Set

//        assertEquals(4, strings.size());
//        assertEquals(HashSet.class, strings.getClass());

        // Add the strings to a TreeSet

//        assertEquals(4, sortedStrings.size());
//        assertEquals(TreeSet.class, sortedStrings.getClass());
//        assertEquals("a", sortedStrings.first());

    }

    @Test
    public void filterWithPredicate() {
//        IntStream.of(3, 1, 4, 1, 5, 9)
//                .filter(n -> true)  // accept even nums only
//                .forEach(n -> assertTrue(n % 2 == 0));
    }
}
