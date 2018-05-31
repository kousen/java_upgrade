package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a",
                                             "list", "of", "strings");

        int total = strings.stream()
                           .peek(System.out::println)
                           .mapToInt(String::length)
                           .peek(System.out::println)
                           .sum();
        System.out.printf("The total length is %d%n", total);

        List<Integer> nums = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);

        List<Integer> collected = nums.parallelStream()
                                    .collect(Collectors.toList());
        // collect respects encounter order -- always get the same order back
        System.out.println(collected);

        List<Integer> results = Collections.synchronizedList(new ArrayList<>());
        nums.parallelStream()
            .forEach(results::add); // does NOT care about encounter order
        System.out.println(results);
    }
}
