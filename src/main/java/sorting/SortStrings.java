package sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortStrings {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

        System.out.println(strings);

        System.out.println("Natural sort:");
        Collections.sort(strings);
        System.out.println(strings);

        System.out.println("Sort by length using a Comparator impl anon inner class:");
        Collections.sort(strings, new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        System.out.println(strings);

        System.out.println("Reverse sort by length with a Comparator impl lambda expression:");
        Collections.sort(strings, (s1, s2) -> s2.length() - s1.length());
        System.out.println(strings);

        // Stream has a sorted() method that is not destructive
        System.out.println("Natural sort using Stream.sorted()");
        List<String> sorted = strings.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sorted);

        System.out.println("Reverse length sort using Stream.sorted(Comparator)");
        sorted = strings.stream()
                .sorted((s1, s2) -> s2.length() - s1.length())
                .collect(Collectors.toList());
        System.out.println(sorted);

        System.out.println("Sort by length using Comparator.comparingInt()");
        sorted = strings.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println(sorted);

        System.out.println("Sort by length, then equal lengths reverse alpha");
        sorted = strings.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.reverseOrder()))
                .collect(Collectors.toList());
        System.out.println(sorted);
    }
}
