package streams;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                .sorted((s1, s2) -> s2.length() - s1.length())
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
                .sorted(Comparator.comparingInt(String::length).reversed()
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
                //.collect(Collectors.toMap(Function.identity(), String::length));
                .collect(Collectors.toMap(s -> s, String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList(null, "this", null, null,
                "is", "a", null, "list", null, "of", "strings", null);

        Predicate<String> nullFilter = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;

        Logger logger = Logger.getLogger(StringExercises.class.getName());
        Consumer<String> logInfo = logger::info;
        Consumer<String> console = System.out::println;

        stringsWithNulls.stream()
                .filter(nullFilter.and(evenLength))
                .forEach(logInfo.andThen(console));

        // Combine the two predicates and use the result to print non-null, even-length strings


        //List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
        int total = IntStream.of(3, 1, 4, 1, 5, 9).sum();
        System.out.println("The total is " + total);
    }

}
