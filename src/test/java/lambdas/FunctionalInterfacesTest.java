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
    public void implementConsumerUsingAnonInnerClass() throws Exception {
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
    public void implementConsumerUsingLambda() throws Exception {
    }

    @Test
    public void implementConsumerUsingMethodReference() throws Exception {
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
        List<String> stringList = Arrays.asList("a b c b c d".split(" "));

        assertEquals(6, stringList.size());
        assertEquals(ArrayList.class, stringList.getClass());

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
        IntStream.of(3, 1, 4, 1, 5, 9)
                .filter(n -> true)  // accept even nums only
                .forEach(n -> assertTrue(n % 2 == 0));
    }
}
