package streams;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReduceTest {
    @Test
    void sumAsReduceOperation() {
        int sum = Stream.of(1, 2, 3, 4, 5)
                .reduce(0, Integer::sum);
        assertEquals(15, sum);
    }

    @Test
    void sumStringsAsReduceOperation() {
        int sum = Stream.of("1", "2", "3", "4", "5")
                .map(Integer::parseInt) // Convert String to Integer
                .reduce(0, Integer::sum);
        assertEquals(15, sum);
    }

    @Test
    void sumIntStream() {
        int sum = IntStream.of(1, 2, 3, 4, 5).sum();
        assertEquals(15, sum);
    }

    @Test
    void productOfEvenNumbers() {
        int product = Stream.of(1, 2, 3, 4, 5)
                .filter(n -> n % 2 == 0)
                .reduce(1, (a, b) -> a * b);
        assertEquals(8, product);
    }

    @Test
    void countLetterR() {
        long rCount = "strawberry".chars()
                .filter(ch -> ch == 'r')
                //.count();
                .reduce(0, (accumulator, b) -> accumulator + 1);
        assertEquals(3, rCount);
    }

    @Test
    // use concat as a binary operator
    void concatStrings() {
        String result = Stream.of("a", "b", "c")
                .reduce("", String::concat);
        assertEquals("abc", result);
    }

    @Test
    void optionalOrElse() {
        String result = Stream.of("a", "b", "c")
                .filter(s -> s.length() > 0)
                .reduce(String::concat).orElse("");
        assertEquals("abc", result);
    }

}
