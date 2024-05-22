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
                .toList();
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
                .toList();
        System.out.println(sorted);
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        List<String> sorted = strings.stream()
                .sorted(this::compareStrings)
                .toList();
        System.out.println(sorted);
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
        List<String> sorted = strings.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .toList();
        System.out.println(sorted);
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        // Add them to a LinkedList
        Collection<String> evenLengthStrings = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evenLengthStrings);
        System.out.println(evenLengthStrings.getClass().getName());

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                //.collect(Collectors.toMap(Function.identity(), String::length));
                .collect(Collectors.toMap(s -> s, String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls = Arrays.asList("this", null, "is", null, "a", "test", null);
        List<String> evens = stringsWithNulls.stream()
                //.peek(System.out::println)
                //.filter(s -> s != null && s.length() % 2 == 0) // guard condition
                //.filter(s -> s != null)
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .toList();
        System.out.println(evens);

        // Function composition
        Logger logger = Logger.getLogger(StringExercises.class.getName());
        Consumer<String> log = logger::info;
        Consumer<String> print = System.out::println;

        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;
        stringsWithNulls.stream()
                .filter(nonNull.and(evenLength))
                .forEach(log.andThen(print));

        // Combine the two predicates and use the result to print non-null, even-length strings

        // f: A -> B, g: B -> C, (g.f)(x) = g(f(x)), A -> C
        Function<String,Integer> length = String::length;
        Function<Integer,String> toBinary = Integer::toBinaryString;
        System.out.println(compose(length, toBinary).apply("hello"));
    }

    // generated by GitHub Copilot
    private <A, B, C> Function<A, C> compose(Function<A, B> f, Function<B, C> g) {
        return x -> g.apply(f.apply(x));
    }

}
