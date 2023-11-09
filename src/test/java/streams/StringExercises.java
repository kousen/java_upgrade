package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StringExercises {
    private final List<String> strings = Arrays.asList("this", "is", "a",
            "list", "of", "strings");

    @SuppressWarnings("Convert2Lambda")
    @Test
    public void stringLengthSort_InnerClass() {     // Java 5, 6, 7
        strings.sort(new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        System.out.println(strings);
        assertThat(strings).containsExactly("a", "is", "of", "this", "list", "strings");
    }

    @SuppressWarnings("ComparatorCombinators")
    @Test
    public void stringLengthSort_lambda() {
        // Use lambda for the Comparator (reverse sort)
        strings.sort((s1, s2) -> s2.length() - s1.length());
        System.out.println(strings);

        // Use the "sorted" method on Stream
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .toList();
        System.out.println(sorted);
        assertThat(sorted).extracting(String::length)
                .isSorted();
    }

    private int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test  // Use a lambda that calls 'compareStrings' directly
    public void stringLengthSort_methodCall() {
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> compareStrings(s1, s2))
                .toList();
        System.out.println(sorted);
        assertThat(sorted).extracting(String::length)
                .isSorted();
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        List<String> sorted = strings.stream()
                .sorted(this::compareStrings)
                .toList();
        System.out.println(sorted);
        assertThat(sorted).extracting(String::length)
                .isSorted();
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
        List<String> sorted = strings.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .toList();
        System.out.println(sorted);
        assertThat(sorted).extracting(String::length)
                .isSorted();
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        // Add them to a LinkedList
        Collection<String> evens = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);
        assertThat(evens).contains("is", "of", "this", "list");

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                .distinct()
                .collect(Collectors.toMap(Function.identity(), String::length));
        //.collect(Collectors.toMap(s -> s, String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList("this", null,
                "is", null, "a", null, "list", null, "of", null, "strings");
        List<String> evenLengths = stringsWithNulls.stream()
                // .filter(s -> s != null && s.length() % 2 == 0) // short-circuiting logical AND
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .toList();
        System.out.println(evenLengths);

        // Function composition
        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;

        Logger logger = Logger.getLogger("demoCollectors");

        Consumer<String> print = System.out::println;
        Consumer<String> log = logger::info;

        // Combine the two predicates and use the result to print non-null, even-length strings
        stringsWithNulls.stream()
                .filter(nonNull.and(evenLength))  // function composition
                .forEach(print.andThen(log));     // function composition

        functionWithConsumer("Hello", String::toUpperCase, System.out::println);
    }

    // Combine a Function with a Consumer
    public <T,R> void functionWithConsumer(T value, Function<T,R> function, Consumer<R> consumer) {
        consumer.accept(function.apply(value));
    }
}
