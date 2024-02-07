package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StringExercisesTest {
    private final List<String> strings = Arrays.asList("this", "is", "a",
            "list", "of", "strings");

    @Test
    void sortByLength_collectons_sort() {
        // Destructive sort -- original list is modified
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        System.out.println(strings);
    }

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
        List<String> sortedStrings = strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .toList();
        System.out.println("Sorted:   " + sortedStrings);
        System.out.println("Original: " + strings);
    }

    private int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @Test  // Use a lambda that calls 'compareStrings' directly
    public void stringLengthSort_methodCall() {
        List<String> sortedStrings = strings.stream()
                .sorted((s1, s2) -> compareStrings(s1, s2))
                .toList();
        System.out.println("Sorted:   " + sortedStrings);
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        List<String> sortedStrings = strings.stream()
                .sorted(this::compareStrings)
                .toList();
        System.out.println("Sorted:   " + sortedStrings);
        assertThat(sortedStrings.stream().map(String::length).toList())
                .containsExactly(1, 2, 2, 4, 4, 7);
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
        List<String> sortedStrings = strings.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .toList();
        System.out.println("Sorted:   " + sortedStrings);
        assertThat(sortedStrings.stream().map(String::length).toList())
                .containsExactly(1, 2, 2, 4, 4, 7);
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        // Turn them into uppercase
        // Add them to a LinkedList
        LinkedList<String> evens = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(evens);

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                // .collect(Collectors.toMap(s -> s, String::length));
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

        // Filter out nulls, then print even-length strings
        List<String> stringWithNulls = Arrays.asList("this", null, "is",
                null, "a", "list", "of", null, "strings", null);
        List<String> evenLengthList = stringWithNulls.stream()
                // .filter(s -> s != null && s.length() % 2 == 0)
                // .filter(s -> s != null)
                .filter(Objects::nonNull)
                .filter(s -> s.length() % 2 == 0)
                .toList();
        System.out.println(evenLengthList);

        // Function composition
        Logger logger = Logger.getLogger("StringExercisesTest");
        Consumer<String> log = logger::info;
        Consumer<String> print = System.out::println;

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> evenLength = s -> s.length() % 2 == 0;

        stringWithNulls.stream()
                .filter(nonNull.and(evenLength))
                .forEach(log.andThen(print));

        composeFPC((String s) -> s.toUpperCase(),
                s -> s.length() % 2 == 0,
                System.out::println)
                .accept("compositions");
    }

    // f: A -> B, g: B -> C, (g.f)(x) = g(f(x)), A -> C
    private <A, B, C> Function<A, C> compose(Function<A, B> f, Function<B, C> g) {
        return x -> g.apply(f.apply(x));
    }

    // combine a function with a predicate with a consumer
    private <A, B> Consumer<A> composeFPC(Function<A, B> f, Predicate<B> p, Consumer<B> c) {
        return x -> {
            B result = f.apply(x);
            if (p.test(result)) {
                c.accept(result);
            }
        };
    }

}
