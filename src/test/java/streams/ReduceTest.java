package streams;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReduceTest {
    @Test
    void reduceIntegers() {
        int total = IntStream.iterate(0, i -> i + 1)
                .limit(10)
                // .reduce(0, (a, b) -> a + b);
                .reduce(0, Integer::sum);
        assertEquals(45, total);
    }

    @Test
    void reduceIntegersVerbose() {
        int total = IntStream.iterate(1, i -> i + 1)
                .limit(10)
                .reduce(0, (accumulator, element) -> {
                    System.out.println("accumulator = " + accumulator + ", element = " + element);
                    return accumulator + element; // becomes the value of the accumulator on the next iteration
                });
        assertEquals(55, total);
    }

    @Test
    void reduceIntegersVerboseWithoutIdentity() {
        int total = IntStream.iterate(1, i -> i + 1)
                .limit(10)
                .reduce((accumulator, element) -> {
                    System.out.println("accumulator = " + accumulator + ", element = " + element);
                    return accumulator + element; // becomes the value of the accumulator on the next iteration
                }).orElse(0);
        assertEquals(55, total);
    }

    @Test
    void reduceTwoTimesIntegersVerboseWithoutIdentity() {
        int total = IntStream.iterate(1, i -> i + 1)
                .limit(10)
                .reduce(0, (accumulator, element) -> {
                    System.out.println("accumulator = " + accumulator + ", element = " + element);
                    return accumulator + 2 * element; // becomes the value of the accumulator on the next iteration
                });
        assertEquals(110, total);
    }

    @Test
    void productIntegersVerboseWithoutIdentity() {
        int total = IntStream.iterate(1, i -> i + 1)
                .limit(5)
                .reduce(1, (accumulator, element) -> {
                    System.out.println("accumulator = " + accumulator + ", element = " + element);
                    return accumulator * element; // becomes the value of the accumulator on the next iteration
                });
        assertEquals(120, total);
    }

    @Test
    void usePeek() {
        IntStream.iterate(1, i -> i + 1)
                .limit(10)
                .map(i -> i * 2)
                .filter(i -> i % 3 == 0)
                .forEach(System.out::println);
    }
}
