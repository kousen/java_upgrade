package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StringExercises {
    private final List<String> strings = Arrays.asList("this", "is", "a",
            "list", "of", "strings");

    @SuppressWarnings("Convert2Lambda")
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

    @SuppressWarnings("ComparatorCombinators")
    @Test
    public void stringLengthSort_lambda() {
        // Use lambda for the Comparator (reverse sort)
        strings.sort((s1, s2) -> s2.length() - s1.length());
        System.out.println(strings);

        // Use the "sorted" method on Stream
        strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .forEach(System.out::println);
    }

    private static int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test  // Use a lambda that calls 'compareStrings' directly
    public void stringLengthSort_methodCall() {
        strings.sort((s1, s2) -> compareStrings(s1, s2));
        System.out.println(strings);
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        strings.sort(StringExercises::compareStrings);
        System.out.println(strings);
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
        strings.sort(Comparator.comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder()));
        System.out.println(strings);
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        // Add them to a LinkedList
        List<String> evens = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                // .collect(Collectors.toCollection(() -> new LinkedList<>()));
                // .collect(Collectors.toCollection(() -> new ArrayList<>(10)));
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);
        System.out.println(evens.getClass().getName());

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                // .collect(Collectors.toMap(s -> s, String::length));
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList("this", null, "is", "a",
                "list", null, "of", "strings", null, null);

        List<String> evenLengths = stringsWithNulls.stream()
                //.filter(s -> s != null && s.length() % 2 == 0)  // short-circuiting logical AND
                // .filter(s -> s != null)
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenLengths);

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> evenFilter = s -> s.length() % 2 == 0;
        Predicate<String> nullFilter = Objects::nonNull;
        evenLengths = stringsWithNulls.stream()
                .filter(nullFilter.and(evenFilter))  // function composition
                .collect(Collectors.toList());
        System.out.println(evenLengths);

        Logger logger = Logger.getLogger("streams.StringExercises");

        Consumer<String> consolePrinter = System.out::println;
        Consumer<String> consoleLogger = logger::info;
        stringsWithNulls.stream()
                .filter(nullFilter.and(evenFilter))
                .forEach(consolePrinter.andThen(consoleLogger));
    }

    @Test
    void maxString() {
        String maxByLength = strings.stream()
                .max(Comparator.comparingInt(String::length)).orElse("");
        System.out.println(maxByLength);

        String maxByAlpha = strings.stream()
                .max(Comparator.naturalOrder()).orElse("");
        System.out.println(maxByAlpha);
    }
}
