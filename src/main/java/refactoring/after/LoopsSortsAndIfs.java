package refactoring.after;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LoopsSortsAndIfs {
    public static void main(String[] args) {

        Arrays.stream("this is an array of strings".split(" "))
                .filter(s -> s.length() % 2 == 0)
                .map(String::toUpperCase)
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);

        IntStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0))
                .forEach((k, v) -> System.out.println(k + " " + v));
    }
}
