package primes.before;

public class PrimeCalculator {
    public static boolean isPrime(int number) {
        int max = (int) (Math.sqrt(number) + 1);

        boolean prime = true;
        for (int index = 2; index <= max; index++) {
            if (number % index == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }
}
