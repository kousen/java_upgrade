package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.*;
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

    // 1..5|6..10|11..15
    //  S1    S2    S3  --> the intermediate sorts say nothing about the overall sort
    //        S
    @Test
    public void stringLengthSort_lambda() {
        // Use lambda for the Comparator (reverse sort)
        strings.sort((s1, s2) -> s2.length() - s1.length());
        System.out.println(strings);

        // Use the "sorted" method on Stream
        String sorted = strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .collect(joining(","));

        System.out.println(sorted);
    }

    private static int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @Test  // Use a lambda that calls 'compareStrings' directly
    public void stringLengthSort_methodCall() {
        String sorted = strings.stream()
                .sorted((s1, s2) -> compareStrings(s1, s2))
                .collect(joining(","));
        System.out.println(sorted);
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        String sorted = strings.stream()
                .sorted(StringExercises::compareStrings)
                .collect(joining(","));
        System.out.println(sorted);
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
        String sorted = strings.stream()
                .sorted(comparingInt(String::length)
                                .thenComparing(reverseOrder()))
                .collect(joining(","));
        System.out.println(sorted);
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        // Add them to a LinkedList

        LinkedList<String> evens = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(toCollection(LinkedList::new));

        evens.forEach(s -> assertEquals(0, s.length() % 2));

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                .collect(toMap(Function.identity(), String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList("this", null, "is", "another",
                null, "list", "of", null, null, null, "strings");

        String evenLengths = stringsWithNulls.stream()
                 .filter(Objects::nonNull)
                //.filter(s -> s != null && s.length() % 2 == 0)
                .filter(s -> s.length() % 2 == 0)
                .collect(joining(","));

        System.out.println(evenLengths);

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;

        evenLengths = stringsWithNulls.stream()
                .filter(nonNull.and(evenLength))
                .collect(Collectors.joining(","));
        System.out.println(evenLengths);
    }

}
