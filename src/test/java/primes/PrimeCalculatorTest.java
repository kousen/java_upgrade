package primes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import primes.after.PrimeCalculator;

import static org.junit.jupiter.api.Assertions.*;

class PrimeCalculatorTest {
    @ParameterizedTest(name = "{0} is prime")
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19})
    void testIsPrime(int number) {
        assertTrue(PrimeCalculator.isPrime(number));
    }

    @ParameterizedTest(name = "{0} is not prime")
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20})
    void testIsNotPrime(int number) {
        assertFalse(PrimeCalculator.isPrime(number));
    }

}