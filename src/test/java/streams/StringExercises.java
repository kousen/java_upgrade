package streams;

import org.junit.jupiter.api.Test;

import java.nio.file.DirectoryStream;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        strings.sort((s1, s2) -> s2.length() - s1.length());
        System.out.println(strings);
        // Use the "sorted" method on Stream
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .collect(Collectors.toList());
        System.out.println(sorted);
    }

    private int compareStrings(String s1, String s2) {
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
                .sorted(this::compareStrings)
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
        List<String> evens = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);
        System.out.println(evens.getClass().getName());

        // Add the strings to a map of string to length
        Map<String,Integer> map = new HashMap<>();
        for (String s : strings) {
            map.put(s, s.length());
        }
        System.out.println(map);

        map = strings.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList("this", null, null, "is", "a",
                null, "list", null, "of", "strings", null, "with", null, "nulls");

        List<String> evenLengthStrings = stringsWithNulls.stream()
                // .filter(s -> s != null && s.length() % 2 == 0)
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenLengthStrings);

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> notNull = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;
        stringsWithNulls.stream()
                .filter(notNull.and(evenLength))
                .forEach(System.out::println);
    }

}
