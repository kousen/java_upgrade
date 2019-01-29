package streams;

import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
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
        Collections.sort(strings, (s1, s2) -> s2.length() - s1.length());
        System.out.println(strings);

        // Use the "sorted" method on Stream
        strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .forEach(System.out::println);
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
        strings.stream()
                .sorted(Comparator.comparingInt(String::length)
                            .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        // Add them to a LinkedList
        LinkedList<String> evenLengthStrings = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));

        // Add the strings to a map of string to length
        Map<String, Integer> stringMap = strings.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));

        stringMap.forEach((string, length) ->
                System.out.printf("The length of '%s' is %d%n", string, length));

        // Filter out nulls, then print even-length strings
        List<String> myStrings = Arrays.asList("this", null, "is", null, null,
                "a", "list", null, "of", null, "strings");
        myStrings.forEach(System.out::println);
        System.out.println("------");

        myStrings.stream()
                .filter(s -> s != null && s.length() % 2 == 0)
                .forEach(System.out::println);

        myStrings.stream()
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .forEach(System.out::println);

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> nonNullPred = Objects::nonNull;
        Predicate<String> evenLengths = s -> s.length() % 2 == 0;

        myStrings.stream()
                .filter(nonNullPred.and(evenLengths))
                .forEach(System.out::println);

    }

}
