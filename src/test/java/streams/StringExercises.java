package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;

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
        System.out.println("Before: " + strings);
        strings.sort((s1, s2) -> s1.length() - s2.length());
        System.out.println("After:  " + strings);

        // Use the "sorted" method on Stream
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> s2.length() - s1.length())
                .collect(toList());
        System.out.println("Sorted: " + sorted);
    }

    private static int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @Test  // Use a lambda that calls 'compareStrings' directly
    public void stringLengthSort_methodCall() {
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> compareStrings(s1, s2))
                .collect(toList());
        System.out.println("Sorted: " + sorted);
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        List<String> sorted = strings.stream()
                .sorted(StringExercises::compareStrings)
                .collect(toList());
        System.out.println("Sorted: " + sorted);
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
        List<String> sorted = strings.stream()
                .sorted(comparingInt(String::length)
                        .thenComparing(naturalOrder()))
                .collect(toList());
        System.out.println("Sorted: " + sorted);
        System.out.println(sorted.getClass().getName());
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
                .collect(Collectors.toMap(Function.identity(), String::length));
        map.forEach((key,value) -> System.out.println(key + ": " + value));

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList(null, "this", null, "is", "a",
                "list", null, "of", null, "strings", "with", null, "nulls");
        LinkedList<String> evensWithoutNulls = stringsWithNulls.stream()
                // .filter(s -> s != null && s.length() % 2 == 0)
                // .filter(s -> s != null)
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evensWithoutNulls);

        Logger logger = Logger.getLogger(StringExercises.class.getName());
        Consumer<String> consoleLogger = logger::info;
        Consumer<String> consolePrinter = System.out::println;

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;
        stringsWithNulls.stream()
                .filter(nonNull.and(evenLength))
                .forEach(consoleLogger.andThen(consolePrinter));
    }

}
