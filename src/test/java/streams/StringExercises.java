package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
        List<String> sorted = strings.stream()
                .sorted(StringExercises::compareStrings)
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
        Deque<String> evens = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toCollection(ArrayDeque::new));
        System.out.println(evens);
        System.out.println(evens.getClass().getName());

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                //.collect(Collectors.toMap(s -> s, String::length));
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList(null, "this", null, null,
                "is", null, "a", "list", null, "of", "strings", null, "with", "nulls");
        List<String> evensWithoutNulls = stringsWithNulls.stream()
                //.filter(s -> s != null && s.length() % 2 == 0)
                //.filter(s -> s != null)
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evensWithoutNulls);

        // Two consumers:
        Logger logger = Logger.getLogger(StringExercises.class.getName());
        Consumer<String> consoleLog = msg -> logger.fine(() -> msg);
        Consumer<String> printer = System.out::println;

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> filterNulls = Objects::nonNull;
        Predicate<String> evenLengths = s -> s.length() % 2 == 0;
        stringsWithNulls.stream()
                .filter(filterNulls.and(evenLengths))  // function composition of predicates
                .forEach(consoleLog.andThen(printer)); // composition of consumers

        Optional<String> maxLengthString = stringsWithNulls.stream()
                .filter(filterNulls)
                .max(Comparator.comparingInt(String::length));
        System.out.println(maxLengthString);

        int total = 1;
        total = IntStream.of(1, 2, 3, 4, 5)
                //.forEach(n -> total += n);
                        // .sum();
                .reduce((accumulator, element) -> {
                    System.out.println("acc = " + accumulator + ", e = " + element);
                    return accumulator * element;
                }).orElse(0);
        System.out.println("Total = " + total);

        // compute product of doubles
        total = IntStream.of(1, 2, 3, 4, 5)
                .reduce(1, (acc, e) -> {
                    System.out.println("acc = " + acc + ", e = " + e);
                    return acc * (2 * e);
                });
        System.out.println("total = " + total);
    }

    // @Test(NullPointerException.class) in JUnit 4
    @Test
    void showExceptionHandling() {
        String y = null;
        assertThrows(NullPointerException.class, () -> y.length());
    }
}
