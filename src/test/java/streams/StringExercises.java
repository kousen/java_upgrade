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
        strings.sort(new Comparator<String>() {
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
        strings.stream()
                .sorted((s1, s2) -> compareStrings(s1, s2))
                .forEach(System.out::println);
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        strings.stream()
                .sorted(StringExercises::compareStrings)
                .forEach(System.out::println);
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
        List<String> stringsWithNulls = Arrays.asList("this", null, "is", "a", null, null,
                "list", null, "with", null, "nulls");

        // Get only strings of even length
        // Add them to a LinkedList
        LinkedList<String> evens = stringsWithNulls.stream()
                .filter(s -> s != null && s.length() % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);

        // Filter out nulls, then print even-length strings
        evens = stringsWithNulls.stream()
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                // .collect(Collectors.toMap(s -> s, String::length));
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

        // Combine the two predicates and use the result to print non-null, even-length strings
        // Predicate<String> nn = Objects::nonNull;
        // Predicate<String> evenLength = s -> s.length() % 2 == 0;
        evens = stringsWithNulls.stream()
                .filter(((Predicate<String>) Objects::nonNull).and(s -> s.length() % 2 == 0))
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);

        Logger myLogger = Logger.getLogger("local");
        Consumer<String> logger = myLogger::info;
        Consumer<String> printer = System.out::println;
        Consumer<String> both = logger.andThen(printer);
        strings.forEach(both);
    }

}
