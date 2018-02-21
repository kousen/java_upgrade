package streams;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings({"Java8ListSort", "ComparatorCombinators", "Convert2MethodRef"})
public class StringExercises {
    private List<String> strings = Arrays.asList("this", "is", "a",
                                                 "list", "of", "strings");

    @Test
    public void stringLengthSort_InnerClass() {
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
        // Use lambda for the Comparator
        Collections.sort(strings, (s1, s2) -> s1.length() - s2.length());

        System.out.println(strings);

        // Use the "sorted" method on Stream
        List<String> sorted = strings.stream()
                                     .sorted()
                                     .collect(Collectors.toList());

        System.out.println(sorted);
        System.out.println(strings);
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
        LinkedList<String> collected = strings.stream()
                                              .filter(s -> s.length() % 2 == 0)
                                              .collect(Collectors.toCollection(LinkedList::new));

        System.out.println(collected);
        System.out.println(collected.getClass().getName());
        collected.forEach(System.out::println);

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
                                          .collect(Collectors.toMap(Function.identity(), String::length));

        map.forEach((word,size) -> System.out.printf("The size of %s is %d%n", word, size));

        List<String> myStrings = Arrays.asList("this", "is", null, "a", null,
                                               "list", "of", null, "strings");

        // Filter out nulls, then print even-length strings
        List<String> sorted = myStrings.stream()
                                        .filter(Objects::nonNull)
                                        .filter(s -> s.length() % 2 == 0)
                                        .collect(Collectors.toList());
        System.out.println(sorted);


        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> evens = s -> s.length() % 2 == 0;

        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> both = nonNull.and(evens);
        myStrings.stream()
                 .filter(both)
                 .forEach(System.out::println);
    }
}
