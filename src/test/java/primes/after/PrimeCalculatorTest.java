package primes.after;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrimeCalculatorTest {

    @Test
    public void isPrime() throws Exception {
        Stream.of(2, 3, 5, 7, 11, 13, 17, 19, 23)
                .forEach(n -> assertTrue(PrimeCalculator.isPrime(n)));

        assertTrue(Stream.of(2, 3, 5, 7, 11, 13, 17, 19, 23)
                .allMatch(PrimeCalculator::isPrime));

        Stream.of(4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22)
                .forEach(n -> assertFalse(PrimeCalculator.isPrime(n)));

        assertTrue(Stream.of(4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22)
                .noneMatch(PrimeCalculator::isPrime));
    }

}