package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringExercises {
    private final List<String> strings = Arrays.asList("this", "is", "a",
            "list", "of", "strings");

    @Test
    public void stringLengthSort_InnerClass() {     // Java 5, 6, 7
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        System.out.println(strings);
    }

    @Test
    public void stringLengthSort_lambda() {
        // Use lambda for the Comparator (reverse sort)
        strings.sort((s1, s2) -> s2.length() - s1.length());
        System.out.println(strings);

        // Use the "sorted" method on Stream
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .collect(Collectors.toList());
        System.out.println(sorted);
        System.out.println(strings);
    }

    private static int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @Test  // Use a lambda that calls 'compareStrings' directly
    public void stringLengthSort_methodCall() {
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> compareStrings(s1, s2))
                .collect(Collectors.toList());
        System.out.println(sorted);
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        List<String> sorted = strings.stream()
                .sorted(StringExercises::compareStrings)
                .collect(Collectors.toList());
        System.out.println(sorted);
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
        List<String> sorted = strings.stream()
                .sorted(Comparator.comparingInt(String::length)
                    .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
        System.out.println(sorted);
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        // Add them to a LinkedList
        LinkedList<String> evens = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                .collect(Collectors.toMap(s -> s, String::length));
                // .collect(Collectors.toMap(Function.identity(), String::length));
        map.forEach((k,v) -> System.out.println(k + " maps to " + v));

        List<String> stringWithNulls = Arrays.asList("this", null, null, "is", "a", null,
                "list", "with", null, "nulls");

        // Filter out nulls, then print even-length strings
        stringWithNulls.stream()
                // .filter(s -> s != null)
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                // .filter(s -> s != null && s.length() % 2 == 0)
                .forEach(System.out::println);

        Predicate<String> evenLength = s -> s.length() % 2 == 0;
        Predicate<String> nonNull = Objects::nonNull;

        // Combine the two predicates and use the result to print non-null, even-length strings
        stringWithNulls.stream()
                .filter(nonNull.and(evenLength))
                .forEach(System.out::println);
    }

    @Test
    void lazyErrorMessage() {
        int x = 5;
        int y = 5;
        assertAll(  // takes a vararg list of Executable...
                () -> assertEquals(x, y, createErrorMessageSupplier(x, y)),
                () -> assertEquals(x, y, () -> createErrorMessage(x, y))
        );
    }

    private Supplier<String> createErrorMessageSupplier(int x, int y) {
        return () -> "Value " + x + " is not equal to value " + y + "!";
    }

    private String createErrorMessage(int x, int y) {
        return "Value " + x + " is not equal to value " + y + "!";
    }

    @Test
    void logMessage() {
        Logger logger = Logger.getLogger("myLogger");
        logger.fine("this is " + " a concatenated " + " message");
        logger.fine(() -> "this is " + " a concatenated " + " message");
    }
}
