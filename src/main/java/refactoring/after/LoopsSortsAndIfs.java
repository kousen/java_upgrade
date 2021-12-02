package refactoring.after;

import java.util.*;
import java.util.stream.Collectors;

public class LoopsSortsAndIfs {
    public static void main(String[] args) {

        // loop --> stream
        // if inside loop --> filter
        // conversion inside loop --> map
        // collect to list, or here just print
        Arrays.stream("this is an array of strings".split(" "))
                .peek(s -> System.out.println("Before filter: " + s))
                .filter(s -> s.length() % 2 == 0)
                .peek(s -> System.out.println("After filter: " + s))
                .map(s -> s.toUpperCase(Locale.ROOT))
                .peek(s -> System.out.println("After map: " + s))
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);

    }
}
