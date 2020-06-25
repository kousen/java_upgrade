package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringExercises {
    private final List<String> strings = Arrays.asList("this", "is", "a",
            "list", "of", "strings");

    // List<String> strings = List.of("this", ...); // Java 9+

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
        List<String> sorted = strings.stream()  // var --> Local Variable Type Inference (LVTI)
                .sorted((s1, s2) -> s1.length() - s2.length())
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(strings);
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
        System.out.println(strings);
        System.out.println(sorted);
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        List<String> sorted = strings.stream()
                .sorted(StringExercises::compareStrings)
                .collect(Collectors.toList());
        System.out.println(strings);
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
                // .collect(Collectors.toMap(s -> s, s -> s.length()));
                // .collect(Collectors.toMap(s -> s, String::length));
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings

        // Combine the two predicates and use the result to print non-null, even-length strings
    }
}
