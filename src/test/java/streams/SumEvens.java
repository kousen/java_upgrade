package streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.IntPredicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumEvens {
    private static final IntPredicate EVENS = n -> n % 2 == 0;
    private static final IntPredicate ODDS = n -> n % 2 != 0;

    @Test
    public void addEvenElements() {
        List<Integer> integers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5);
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
    }

    @Test
    public void addOddElementsUsingStreams() {
    }

}
