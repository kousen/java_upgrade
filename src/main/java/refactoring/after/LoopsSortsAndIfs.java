package refactoring.after;

import java.util.Arrays;
import java.util.Comparator;

public class LoopsSortsAndIfs {
    public static void main(String[] args) {
        Arrays.stream("this is an array of strings".split(" "))
                .filter(s -> s.length() % 2 == 0)
                .map(String::toUpperCase)
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }
}
