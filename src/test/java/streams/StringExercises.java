package streams;

import org.junit.jupiter.api.Test;
import sun.security.rsa.RSAUtil;

import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringExercises {
    private final List<String> strings = Arrays.asList("this", "is", "a",
            "list", "of", "strings");

    @SuppressWarnings("Convert2Lambda")
    @Test
    public void stringLengthSort_InnerClass() {     // Java 5, 6, 7
        strings.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        System.out.println(strings);
    }

    @SuppressWarnings("ComparatorCombinators")
    @Test
    public void stringLengthSort_lambda() {
        // Use lambda for the Comparator (reverse sort)
        strings.sort((s1, s2) -> s2.length() - s1.length());
        System.out.println(strings);

        // Use the "sorted" method on Stream
        strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .forEach(System.out::println);
        System.out.println(strings);
    }

    private static int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test  // Use a lambda that calls 'compareStrings' directly
    public void stringLengthSort_methodCall() {
        strings.sort((s1, s2) -> compareStrings(s1, s2));
        System.out.println(strings);
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        strings.sort(StringExercises::compareStrings);
        System.out.println(strings);
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
        strings.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        // Add them to a LinkedList
        List<String> evens = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);
        System.out.println(evens.getClass().getName());

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                //.collect(Collectors.toMap(s -> s, s -> s.length()));
                //.collect(Collectors.toMap(Function.identity(), String::length));
                .collect(Collectors.toMap(Function.identity(), String::length));
                //.collect(Collectors.toMap(s -> s, String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList("this", null, "is", null, null, "a",
                "list", "of", null, "strings", null);
        Logger logger = Logger.getLogger("StringExercises");
        stringsWithNulls.stream()
                // .filter(s -> s != null && s.length() % 2 == 0)  // short-circuiting logical AND
                // .filter(s -> s != null)
                .peek(s -> logger.fine(() -> "Before null check: " + s))
                .filter(Objects::nonNull)
                .peek(s -> logger.fine(() -> "After null check, before even length check: " + s))
                .filter(s -> s.length() % 2 == 0)
                .peek(s -> logger.fine(() -> "After even length check: " + s))
                .forEach(System.out::println);

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;
        Consumer<String> consolePrint = System.out::println;
        Consumer<String> consolelogger = logger::info;
        stringsWithNulls.stream()
                .filter(nonNull.and(evenLength))  // function composition
                .forEach(consolePrint.andThen(consolelogger));
    }

    @Test
    void reduceDemo() {
        int a = 3;
        int b = 4;
        int c = a + b;
        System.out.println(c);
        c = Integer.sum(a, b);
        System.out.println(c);

        OptionalInt totalOptional = IntStream.iterate(0, i -> i + 1)
                .peek(x -> System.out.println("Before filter: " + x))
                .filter(i -> i > 10)
                .peek(x -> System.out.println("After filter: " + x))
                .limit(10)
                .reduce(Integer::sum);
        System.out.println(totalOptional);

        Optional<Integer> optional = Stream.iterate(0, i -> i + 1)
                .limit(10)
                .reduce(Integer::sum);
        // .reduce((a1, b1) -> Integer.sum(a1, b1));
        System.out.println(optional);

        int total = IntStream.iterate(0, i -> i + 1)
                .limit(10)
                .filter(i -> i > 10)
                .sum();
        System.out.println(total);
    }

    @Test
    void reduceDemoVerbose() {
        Optional<Integer> optional = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .reduce((accumulator, element) -> {
                    System.out.println("accumulator = " + accumulator + ", element = " + element);
                    // the accumulator is whatever the BinaryOperator returns on this pass
                    return accumulator + element;
                });
        // .reduce((a1, b1) -> Integer.sum(a1, b1));
        System.out.println(optional);
    }

    @Test
    void reduceDemoVerboseWithIdentity() {
        Integer total = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .reduce(0, (accumulator, element) -> {
                    System.out.println("accumulator = " + accumulator + ", element = " + element);
                    // the accumulator is whatever the BinaryOperator returns on this pass
                    return accumulator + element;
                });
        // .reduce((a1, b1) -> Integer.sum(a1, b1));
        System.out.println(total);
    }

    @Test
    void sumBigDecimals() {
        BigDecimal total = Stream.iterate(BigDecimal.ONE, i -> i.add(BigDecimal.ONE))
                .limit(10)
                // .reduce(BigDecimal.ZERO, BigDecimal::add);
                .reduce(BigDecimal.ZERO, (accumlator, element) -> {
                    System.out.println("accumlator = " + accumlator + ", element = " + element);
                    return accumlator.add(element);
                });
        System.out.println(total);
    }

    @Test
    void randomDoubles() {
        System.out.println(
                DoubleStream.generate(Math::random)
                        .limit(1_000_000)
                        .summaryStatistics());
        //.forEach(System.out::println);
    }
}
