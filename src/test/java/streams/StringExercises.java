package streams;

import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

public class StringExercises {
    private final List<String> strings = Arrays.asList("this", "is", "a",
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
        System.out.println(strings);
        strings.sort((s1, s2) -> s2.length() - s1.length());
        System.out.println(strings);

        // Use the "sorted" method on Stream
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .collect(toList());
        System.out.println(sorted);
    }

    private static int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @Test  // Use a lambda that calls 'compareStrings' directly
    public void stringLengthSort_methodCall() {
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> compareStrings(s1, s2))
                .collect(toList());
        System.out.println(sorted);
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        List<String> sorted = strings.stream()
                .sorted(StringExercises::compareStrings)
                .collect(toList());
        System.out.println(sorted);
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
        List<String> sorted = strings.stream()
                .distinct()
                .sorted(comparingInt(String::length)
                    .thenComparing(reverseOrder()))
                .collect(toList());
        System.out.println(sorted);
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        // Add them to a LinkedList
        List<String> evens = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                .collect(Collectors.toMap(s -> s, String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList(null, "this", null, "is", "a",
                null, "list", "with", null, "nulls");

        stringsWithNulls.stream()
                .peek(s -> System.out.println("Before null filter: " + s))
                .filter(Objects::nonNull)
                .peek(s -> System.out.println("After null filter: " + s))
                .filter(s -> s.length() % 2 == 0)
                .forEach(x -> System.out.println("After even filter: " + x));

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> evenLengths = s -> s.length() % 2 == 0;
        Predicate<String> nonNull = Objects::nonNull;

        stringsWithNulls.stream()
                .filter(nonNull.and(evenLengths))
                .forEach(System.out::println);
    }

}
