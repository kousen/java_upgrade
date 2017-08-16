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
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("Hello from a lambda expression");
    }

    @Test
    public void implementConsumerUsingMethodReference() throws Exception {
        Consumer<String> consumer = System.out::println;
        consumer.accept("Hello from a method reference");
    }

    @Test
    public void stringLengthMethod() throws Exception {
        String s = "this is a string";
        System.out.println(s.length());
        Arrays.stream(s.split(" "))
                .forEach(str -> System.out.println(str.length()));
        Arrays.stream(s.split(" "))
                .map(str -> str.length())
                .forEach(System.out::println);
        Arrays.stream(s.split(" "))
                .map(String::length)
                .forEach(System.out::println);
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
        Supplier<Double> supplier = () -> Math.random();
        double val = supplier.get();

        assertTrue(val >= 0.0);
        assertTrue(val <= 1.0);

        // Create a DoubleSupplier that does the same

        DoubleSupplier doubleSupplier = Math::random;
        val = doubleSupplier.getAsDouble();
        assertTrue(val >= 0.0);
        assertTrue(val <= 1.0);
    }

    @SuppressWarnings("SimplifyStreamApiCallChains")
    @Test
    public void constructorReference() throws Exception {
        List<String> stringList = Arrays.asList("a b c b c d".split(" "));

        assertEquals(6, stringList.size());

        // Add the strings to a Set
        Set<String> strings = stringList.stream()
                .collect(Collectors.toSet());

        assertEquals(4, strings.size());
        assertEquals(HashSet.class, strings.getClass());

        // Add the strings to a TreeSet
        TreeSet<String> sortedStrings = stringList.stream()
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
