package primes.after;

import java.util.stream.IntStream;

public class PrimeCalculator {
    public static boolean isPrime(int number) {
        if (number < 0) throw new IllegalArgumentException("Number must be positive");
        int max = (int) (Math.sqrt(number) + 1);
        return number == 2 ||
                IntStream.range(2, max)
                        .noneMatch(index -> number % index == 0);
    }
}
