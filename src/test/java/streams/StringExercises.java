package streams;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        strings.sort((s1, s2) -> s2.length() - s1.length());
        System.out.println(strings);

        // Use the "sorted" method on Stream
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .collect(Collectors.toList());
        System.out.println(sorted);
        System.out.println(strings);
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
                // .collect(Collectors.toCollection(() -> new LinkedList<>()));
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);
        System.out.println(evens.getClass().getName());

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        map.forEach((string,length) -> System.out.println(string + " has length " + length));


        List<String> stringsWithNulls = Arrays.asList("this", null, "is", "a", null,
                "list", null, "of", "string", null, "with", "nulls");

        // Filter out nulls, then print even-length strings
        evens = stringsWithNulls.stream()
                // use a "guard" condition in a short-circuiting &&
                .filter(s -> s != null && s.length() % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evens);

        evens = stringsWithNulls.stream()
                // use two filters
                .filter(s -> s != null)
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evens);

        evens = stringsWithNulls.stream()
                // filter out nulls using Objects::nonNull
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evens);

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;
        evens = stringsWithNulls.stream()
                .filter(nonNull.and(evenLength))
                .collect(Collectors.toList());
        System.out.println(evens);


        Logger log = Logger.getLogger("mylogger");
        Consumer<String> consolePrint = System.out::println;
        Consumer<String> logger = log::info;
        evens.forEach(consolePrint.andThen(logger));
    }

    public List<String> filterByPredicate(List<String> strings, Predicate<String> predicate) {
        return strings.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

}
