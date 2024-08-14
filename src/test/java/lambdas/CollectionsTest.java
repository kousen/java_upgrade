package lambdas;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CollectionsTest {

    @Test
    void forEach_with_collections() {
        List<String> strings = List.of("this", "is", "a", "list", "of", "strings");
        List<Integer> lengths = new ArrayList<>();

        // Old school, but may have concurrency issues
        for (String string : strings) {
            lengths.add(string.length());
        }
        System.out.println(lengths);

        // functional way (collect respects "order")
        lengths = strings.parallelStream()
                .peek(x -> System.out.println(x + "on thread " + Thread.currentThread().getName()))
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(lengths);

        // forEach does NOT respect order
        List<Integer> stringLengths = new ArrayList<>();
        strings.parallelStream().forEachOrdered( str -> stringLengths.add(str.length()));
        System.out.println(stringLengths);

        Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 2);
        map.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
