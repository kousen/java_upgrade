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
//        Supplier<String> supplier = /* ... lambda ... */
//        assertEquals("Hello", supplier.get());
        Supplier<String> supplier = () -> "Hello";
        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingMethodReference() throws Exception {
        Supplier<Double> supplier = () -> Math.random();
        assertTrue(supplier.get() >= 0.0);
        assertTrue(supplier.get() <= 1.0);

        DoubleSupplier doubleSupplier = Math::random;
        assertTrue(doubleSupplier.getAsDouble() >= 0.0);
        assertTrue(doubleSupplier.getAsDouble() <= 1.0);
    }

    @Test
    public void constructorReference() throws Exception {
        // Use Collectors.toCollection with a constructor ref for TreeSet
        Collection<String> strings = Stream.of("a b c b c d".split(" "))
                .collect(Collectors.toSet());

        assertEquals(4, strings.size());
        assertEquals(HashSet.class, strings.getClass());

        Collection<String> stringList = Stream.of("a b c b c d".split(" "))
                .collect(Collectors.toList());

        assertEquals(6, stringList.size());
        assertEquals(ArrayList.class, stringList.getClass());

        TreeSet<String> sortedStrings = Stream.of("a b c b c d".split(" ")) // Stream<String>
                .collect(Collectors.toCollection(TreeSet::new));

        assertEquals(4, sortedStrings.size());
        assertEquals(TreeSet.class, sortedStrings.getClass());
        assertEquals("a", sortedStrings.first());

        ArrayList<Object> collection = Stream.of("a b c b c d".split(" "))
                // .parallel() // operations will use a ForkJoinPool automatically
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        assertEquals(6, collection.size());
        assertEquals(ArrayList.class, collection.getClass());
    }

    private int doubleIt(int n) {
        System.out.println("in doubleIt, n = " + n);
        return 2 * n;
    }

    private boolean mod3(int n) {
        System.out.println("in mod3, n = " + n);
        return n % 3 == 0;
    }

    @Test
    public void demonstrateExtractedMethods() throws Exception {
        OptionalInt first = IntStream.rangeClosed(100, 2_000_000)
                // .parallel()
                .map(this::doubleIt)
                .filter(this::mod3)
                .findFirst();
        System.out.println(first);
        assertEquals(204, first.orElse(0));
    }

    @Test
    public void demonstratePeek() throws Exception {
        IntPredicate mod3 = n -> n % 3 == 0;
        IntPredicate tooBig = n -> n > 4_000_000;

        OptionalInt first = IntStream.rangeClosed(100, 2_000_000)
                .map(n -> 2 * n)
                // .filter(n -> n % 3 == 0 && n > 4_000_000)
                // .filter(mod3.and(tooBig))
                .map(n -> {
                    System.out.println("After doubling, before filter, n = " + n);
                    return n;
                })
                .filter(n -> n % 3 == 0)
                .peek(n -> System.out.println("After filter, n = " + n))
                .findFirst();
        System.out.println(first);
        assertEquals(204, first.orElse(0));
    }

    @Test
    public void filterWithPredicate() throws Exception {
        IntStream.of(3, 1, 4, 1, 5, 9)
                .filter(n -> true)  // replace with Predicate
                .forEach(n -> assertTrue(n % 2 == 0));
    }


}
