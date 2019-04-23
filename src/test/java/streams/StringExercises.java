package streams;

import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class StringExercises {
    private List<String> strings = Arrays.asList("this", "is", "a",
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
                .sorted(Comparator.comparingInt(String::length))
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
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toMap(s -> s, String::length));

        map.forEach((word, len) -> System.out.println(word + ": " + len));

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList("this", null, "is",
                null, "a", "list", null, "of", "strings", null, "with", "nulls");

        List<String> evenLengths = stringsWithNulls.stream()
//                .filter(s -> s != null && s.length() % 2 == 0)
                .peek(x -> System.out.println("Before null check: " + x))
                .filter(Objects::nonNull)
                .peek(x -> System.out.println("After null check: " + x))
                .filter(s -> s.length() % 2 == 0)
                .peek(x -> System.out.println("After even filter: " + x))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(evenLengths);

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> nonNulls = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;
        stringsWithNulls.stream()
                .filter(nonNulls.and(evenLength))
                .forEach(System.out::println);

        Logger logger = Logger.getLogger("MyLogger");
        Consumer<String> log = logger::info;
        Consumer<String> printer = System.out::println;
        stringsWithNulls.forEach(log.andThen(printer));

        stringsWithNulls.stream()
                .filter(nonNulls)
                .forEach(s -> logger.fine(() -> "After filtering: " + s
                    + ", valid values are: "));
    }

}
