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
//        consumer.accept("Hello, World!");
    }

    @Test
    public void implementConsumerUsingMethodReference() throws Exception {
//        consumer.accept("Hello, World!");
    }

    @Test
    public void implementSupplierUsingAnonInnerClass() throws Exception {
//        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingLambda() throws Exception {
//        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingMethodReference() throws Exception {
        // Create a Supplier<Double> that calls Math.random()
//        assertTrue(supplier.get() >= 0.0);
//        assertTrue(supplier.get() <= 1.0);

        // Create a DoubleSupplier that does the same
//        assertTrue(doubleSupplier.getAsDouble() >= 0.0);
//        assertTrue(doubleSupplier.getAsDouble() <= 1.0);
    }

    @Test
    public void constructorReference() throws Exception {
//        List<String> stringList = List.of("a", "b", "b", "c", "d", "d");
//        assertEquals(6, stringList.size());

        // Add the strings to a Set
//        assertEquals(4, strings.size());
//        assertEquals(HashSet.class, strings.getClass());

        // Add the strings to a TreeSet
//        assertEquals(4, sortedStrings.size());
//        assertEquals(TreeSet.class, sortedStrings.getClass());
//        assertEquals("a", sortedStrings.first());
    }

    @Test
    public void filterWithPredicate() throws Exception {
//        IntStream.of(3, 1, 4, 1, 5, 9)
//                .filter(n -> true)  // accept even nums only
//                .forEach(n -> assertTrue(n % 2 == 0));
    }
}
