package lambdas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class FunctionalInterfacesTest {

    @Test
    public void implementConsumerUsingAnonInnerClass() throws Exception {
        assertNull(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Hello, World!");
            }
        });
    }

    @Test
    public void implementConsumerUsingLambda() throws Exception {
        // assertNull(/* ... lambda ... */);
    }
    @Test
    public void implementConsumerUsingMethodReference() throws Exception {
        // assertNull(/* ... method reference ... */);
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
//        Supplier<String> supplier = /* ... lambda ... */
//        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingMethodReference() throws Exception {
        Supplier<Double> supplier = () -> Math.random();
        assertTrue(supplier.get() >= 0.0);
        assertTrue(supplier.get() <= 1.0);
    }

    @Test
    public void constructorReference() throws Exception {
        // Use Collectors.toCollection with a constructor ref for TreeSet
        Collection<String> strings = Stream.of("a b c b c d".split(" "))
                .collect(Collectors.toSet());

        assertEquals(4, strings.size());
        assertEquals("a", ((TreeSet) strings).first());
    }

    @Test
    public void filterWithPredicate() throws Exception {
        IntStream.of(3, 1, 4, 1, 5, 9)
                .filter(n -> true)  // replace with Predicate
                .forEach(n -> assertTrue(n % 2 == 0));
    }


}
