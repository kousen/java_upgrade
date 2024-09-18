package streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SortListTest {
    private final List<String> list =
            List.of("banana", "apple", "orange", "kiwi", "grape");

    @Test
    void sortList_collections() {
        // throws UnsupportedOperationException because
        // List.of() returns an unmodifiable list
//        Collections.sort(list);
//        System.out.println(list);
        List<String> sorted = list.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sorted);
        System.out.println(list);
    }
}
