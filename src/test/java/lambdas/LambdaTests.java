package lambdas;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class LambdaTests {
    @Test
    public void maxAsMethodReference() {
        int max = IntStream.of(3, 1, 4, 1, 5, 9)
                .reduce(Integer.MIN_VALUE, Math::max);
        assertEquals(9, max);

        max = IntStream.of(3, 1, 4, 1, 5, 9).max().orElse(0);
    }

    @Test
    public void consumeAsMethodRef() {
        List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        integers.forEach(x -> System.out.println("The value of x is " + x));
    }
}
