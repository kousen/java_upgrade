package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
                .collect(toList());
        System.out.println(sorted);
    }

    private static int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @Test  // Use a lambda that calls 'compareStrings' directly
    public void stringLengthSort_methodCall() {
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> compareStrings(s1, s2))
                .collect(toList());
        System.out.println(sorted);
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        List<String> sorted = strings.stream()
                .sorted(StringExercises::compareStrings)
                .collect(toList());
        System.out.println(sorted);
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
        List<String> sorted = strings.stream()
                .sorted(comparingInt(String::length)
                        .thenComparing(naturalOrder()))
                .collect(toList());
        System.out.println(sorted);
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        // Add them to a LinkedList
        List<String> evens = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toCollection(() -> new LinkedList<String>()));
        //.collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);
        System.out.println(evens.getClass()
                .getName());

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                // .collect(Collectors.toMap(s -> s, String::length));
                .collect(Collectors.toMap(Function.identity(), string -> string.length()));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList(null, "this", null, null,
                "is", "another", null, "list", null, "of", "strings");
        stringsWithNulls.stream()
                .sorted(nullsFirst(naturalOrder()))
                .forEach(System.out::println);
        stringsWithNulls.stream()
                .sorted(nullsLast(naturalOrder()))
                .forEach(System.out::println);

        stringsWithNulls.stream()
                // .filter(s -> s != null && s.length() % 2 == 0)
                // .filter(s -> s != null)
                .filter(Objects::nonNull)  // filters out any nulls
                .filter(s -> s.length() % 2 == 0)
                .forEach(System.out::println);

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;
        stringsWithNulls.stream()
                .filter(nonNull.and(evenLength)) // function composition
                .forEach(System.out::println);

        Logger logger = Logger.getLogger("mylogger");
        logger.fine(() -> "Here is my log message at info level");
        Consumer<String> log = message -> logger.info(message);
        Consumer<String> print = message -> System.out.println(message);
        stringsWithNulls.stream()
                .filter(nonNull.and(evenLength))
        //        .forEach(log.andThen(print));
                .forEach(string -> {
                    logger.info(string);
                    System.out.println(string);
                });
    }
}
