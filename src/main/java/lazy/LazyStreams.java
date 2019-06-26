package lazy;

import java.util.logging.Logger;
import java.util.stream.IntStream;

public class LazyStreams {
    private static Logger logger = Logger.getLogger(LazyStreams.class.getName());

    public static int multByTwo(int n) {
        System.out.printf("Inside multByTwo with arg %d on thread %s%n",
                n, Thread.currentThread().getName());
        return n * 2;
    }

    public static boolean modByThree(int n) {
        System.out.printf("Inside modByThree with arg %d on thread %s%n",
                n, Thread.currentThread().getName());
        return n % 3 == 0;
    }

    public static void main(String[] args) {
        // multiply numbers between 100 and 200 by 2, then find first n divisible by 3
        int firstEvenDoubleDivBy3 = IntStream.range(100, 200)
                .peek(n -> logger.fine(() -> "Before doubling: " + n))
                .map(n -> n * 2)
                .peek(n -> System.out.println("After doubling, before filtering: " + n))
                .filter(n -> n % 3 == 0)
                .peek(n -> System.out.println("After filtering: " + n))
                .findFirst().orElse(0);
        System.out.println(firstEvenDoubleDivBy3);


        // Demonstrate laziness using print statements
        firstEvenDoubleDivBy3 = IntStream.range(100, 2_000_000)
                .map(LazyStreams::multByTwo)
                .filter(LazyStreams::modByThree)
                .findFirst().orElse(0);
        System.out.printf("First even divisible by 3 is %d%n", firstEvenDoubleDivBy3);

        // No data consumed without a terminal operation
        IntStream intStream = IntStream.range(100, 2000000)
                .map(LazyStreams::multByTwo)
                .filter(LazyStreams::modByThree);

    }
}
