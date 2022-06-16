package streams;

import org.junit.jupiter.api.Test;
import sun.security.rsa.RSAUtil;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .forEach(System.out::println);
        System.out.println(strings);
    }

    private static int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

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
        strings.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }

    @Test
    public void demoCollectors() {
        reduceDemo();

        // Get only strings of even length
        // Add them to a LinkedList

        // Add the strings to a map of string to length

        // Filter out nulls, then print even-length strings

        // Combine the two predicates and use the result to print non-null, even-length strings
    }

    private void reduceDemo() {
        int a = 3;
        int b = 4;
        int c = a + b;
        System.out.println(c);
        c = Integer.sum(a, b);
        System.out.println(c);

        OptionalInt totalOptional = IntStream.iterate(0, i -> i + 1)
                .peek(x -> System.out.println("Before filter: " + x))
                .filter(i -> i > 10)
                .peek(x -> System.out.println("After filter: " + x))
                .limit(10)
                .reduce(Integer::sum);
        System.out.println(totalOptional);

        Optional<Integer> optional = Stream.iterate(0, i -> i + 1)
                .limit(10)
                .reduce(Integer::sum);
                // .reduce((a1, b1) -> Integer.sum(a1, b1));
        System.out.println(optional);

        int total =IntStream.iterate(0, i -> i + 1)
                .limit(10)
                .filter(i -> i > 10)
                .sum();
        System.out.println(total);
    }

}
