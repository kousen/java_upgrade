package streams;

import java.util.stream.IntStream;

public class GenerateStream {
    public static void main(String[] args) {
        IntStream.iterate(1, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);
    }
}
