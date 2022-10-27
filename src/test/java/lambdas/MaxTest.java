package lambdas;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class MaxTest {
    @Test
    void getMaxFromStream() {
        int max = IntStream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .reduce((accumulator, element) -> {
                    System.out.println("accumulator = " + accumulator + ", element = " + element);
                    return Integer.max(accumulator, element);
                })
                .orElse(0);
        System.out.println(max);
    }

    @Test
    void getMaxFromStreamWithIdentity() {
        int max = IntStream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .reduce(Integer.MIN_VALUE, (accumulator, element) -> {
                    System.out.println("accumulator = " + accumulator + ", element = " + element);
                    return Integer.max(accumulator, element);
                });
        System.out.println(max);
    }

    @Test
    void getMaxFromStreamWithIdentityMinimized() {
        int max = IntStream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println(max);
    }
}
