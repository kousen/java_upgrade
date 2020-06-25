package lazy;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class LazyStreams {
    private static final Logger logger = Logger.getLogger(LazyStreams.class.getName());

    public static int multByTwo(int n) {
        System.out.printf("Inside multByTwo with arg %d%n", n);
        return n * 2;
    }

    public static boolean modByThree(int n) {
        System.out.printf("Inside modByThree with arg %d%n", n);
        return n % 3 == 0;
    }

    public static void main(String[] args) {
        // multiply numbers between 100 and 200 by 2, then find first n divisible by 3
        int firstEvenDoubleDivBy3 = IntStream.range(100, 200)
                .peek(n -> System.out.println("The current value of n is " + n))
                .filter(n -> n % 3 == 0)
                .peek(n -> System.out.println("After filter, n is " + n))
                .map(n -> n * 2)
                .peek(n -> System.out.println("After map, n is " + n))
                .findFirst().orElse(-1);
        System.out.println(firstEvenDoubleDivBy3);


        // Demonstrate laziness using print statements
        firstEvenDoubleDivBy3 = IntStream.range(100, 2_000_000)
                .filter(LazyStreams::modByThree)
                .map(LazyStreams::multByTwo)
                .findFirst().orElse(-1);
        System.out.printf("First even divisible by 3 is %d%n", firstEvenDoubleDivBy3);
    }
}
