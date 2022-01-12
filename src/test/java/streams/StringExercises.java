package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
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
        strings.stream()
               .sorted(StringExercises::compareStrings)
               .forEach(System.out::println);
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
                                          // .collect(Collectors.toCollection(() -> new LinkedList<>()));
                                          .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);
        System.out.println(evens.getClass()
                                .getName());

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                                          //.collect(Collectors.toMap(s -> s, s -> s.length()));
                                          // .collect(Collectors.toMap(s -> s, String::length));
                                          .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList(null, "this", "is", null, "a", null,
                "list", "with", null, "nulls");
        stringsWithNulls.stream()
                // .filter(s -> s != null && s.length() % 2 == 0) // short-circuiting logical and operator
                //.filter(s -> s != null)
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .forEach(System.out::println);

        // Combine the two predicates and use the result to print non-null, even-length strings
        // compose predicates with and, or, and negate
        Predicate<String> evenLength = s -> s.length() % 2 == 0;
        Predicate<String> notNull = Objects::nonNull;

        stringsWithNulls.stream()
                .filter(notNull.and(evenLength))
                .forEach(System.out::println);
    }

}
