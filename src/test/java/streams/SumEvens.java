package streams;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumEvens {
    public static final IntPredicate EVENS = n -> n % 2 == 0;
    public static final IntPredicate ODDS = n -> n % 2 != 0;
    private final List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);

    @Test
    public void addEvenElements() {
        int sum = 0;
        for (int n : integers) {
            if (n % 2 == 0) {
                sum += n;
            }
        }
        assertEquals(12, sum);
    }

    public int addElements(IntPredicate predicate) {
        return integers.stream()
                .mapToInt(Integer::intValue)
                .filter(predicate)
                .sum();
    }

    @Test
    public void addEvenElementsUsingStreams() {
        assertEquals(12, addElements(EVENS));
    }

    @Test
    public void addOddElementsUsingStreams() {
        assertEquals(24, addElements(ODDS));
    }

}
