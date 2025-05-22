package streams;

import java.util.ArrayList;
import java.util.List;

public class Ordering {
    public static void main(String[] args) {
        List<Integer> ints = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Collect doubles into a generated list
        List<Integer> doubles = ints.stream()
                .map(n -> n * 2)
                .parallel()
                .toList();
        System.out.println(doubles);

        // Add doubles to the list using add inside foreach
        List<Integer> doublesList = new ArrayList<>();
        ints.stream()
                .map(n -> n * 2)
                .parallel()
                .forEach(doublesList::add);
        System.out.println(doublesList);
    }
}
