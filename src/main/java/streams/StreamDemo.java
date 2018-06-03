package streams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        BigDecimal two = new BigDecimal("2.0");
        BigDecimal sum = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                               .limit(10)
                               .reduce(BigDecimal.ZERO,
                                       (accumulator, val) -> {
                                   System.out.println("accumulator = " +
                                    accumulator + ", val = " + val);
                                   return accumulator.add(val.multiply(two));
                               });
        System.out.println("The sum of twice the first 10 big decimals is " + sum);

        sum = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                               .limit(10)
                               .reduce(BigDecimal.ZERO,
                                       (accumulator, val) -> accumulator.add(val.multiply(two)));
        System.out.println("The sum of the first 10 big decimals is " + sum);
    }
}
