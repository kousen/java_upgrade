package streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoxedStreamDemo {
    public static void main(String[] args) {
        List<String> strings = Stream.of("this", "is", "a", "stream")
                .collect(Collectors.toList());
        System.out.println(strings);

        List<Integer> ints = IntStream.of(3, 1, 4, 1, 5, 9)
                // .mapToObj(Integer::valueOf)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(ints);
    }
}
