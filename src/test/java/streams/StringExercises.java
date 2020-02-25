package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class StringExercises {
    private List<String> strings = Arrays.asList("this", "is", "a",
            "list", "of", "strings");

    private Logger log = Logger.getLogger(StringExercises.class.getName());

    @Test
    public void stringLengthSort_InnerClass() {     // Java 5, 6, 7
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        System.out.println(strings);

        assertAll(() -> assertEquals(6, strings.size()));
    }

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
                .sorted(Comparator.comparingInt(String::length))
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
        evens.forEach(s -> assertEquals(0, s.length() % 2));

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                .collect(Collectors.toMap(s -> s, String::length));
        map.forEach((k,v) -> System.out.println(k + " maps to " + v));

        map = strings.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList("this", null, "is", null, null, "a",
                "list", null, "with", "nulls", null);
        stringsWithNulls.stream()
                .filter(s -> s != null)
                .filter(s -> s.length() % 2 == 0)
                .forEach(System.out::println);

        stringsWithNulls.stream()
                .filter(s -> s != null && s.length() % 2 == 0)
                .forEach(System.out::println);

        stringsWithNulls.stream()
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .forEach(System.out::println);

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;
        Predicate<String> composite = nonNull.and(evenLength);
        stringsWithNulls.stream()
                .filter(composite)
                .forEach(System.out::println);

        Consumer<String> printer = System.out::println;
        Consumer<String> logger = log::info;
        Consumer<String> printAndLog = printer.andThen(logger);
        strings.forEach(printAndLog);
    }

}
