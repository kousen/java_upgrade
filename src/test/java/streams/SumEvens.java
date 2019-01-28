package streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class SumEvens {
    private static final IntPredicate EVENS = n -> n % 2 == 0;
    private static final IntPredicate ODDS = n -> n % 2 != 0;

    @Test
    public void addEvenElements() {
        List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
        int sum = 0;
        for (int n : integers) {
            if (n % 2 == 0) {
                sum += n;
            }
        }
        assertEquals(12, sum);
    }

    @Test
    public void addEvenElementsUsingStreams() {
        int sum = IntStream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .filter(EVENS)
                .sum();
        assertEquals(12, sum);
    }

    @Test
    public void addOddElementsUsingStreams() {
        int sum = IntStream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .filter(ODDS)
                .sum();
        assertEquals(24, sum);
    }

}
