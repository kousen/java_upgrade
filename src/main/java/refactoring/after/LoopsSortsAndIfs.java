package refactoring.after;

import java.util.Arrays;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.naturalOrder;

public class LoopsSortsAndIfs {
    public static void main(String[] args) {
        Arrays.stream("this is an array of strings".split(" "))
                .filter(s -> s.length() % 2 == 0)
                .sorted(comparingInt(String::length).thenComparing(naturalOrder()))
                .forEach(System.out::println);
    }
}
