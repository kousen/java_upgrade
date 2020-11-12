package lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderedStreams {
    public static void main(String[] args) {
        List<Integer> doubles = Stream.of(1, 2, 3, 4, 5)
                .map(n -> n * 2)
                .parallel()
                .collect(Collectors.toList()); // collect respects "encounter order"
        System.out.println(doubles);

        List<Integer> alsoDoubles = new ArrayList<Integer>();
        Stream.of(1, 2, 3, 4, 5)
                .map(n -> n * 2)
                .parallel()
                // .forEach(n -> alsoDoubles.add(n));  // forEach does NOT respect encounter order
                .forEach(alsoDoubles::add);
        System.out.println(alsoDoubles);
    }
}
