package lazy;

import java.util.logging.Logger;
import java.util.stream.IntStream;

public class LazyStreams {
    public static int multByTwo(int n) {
        System.out.printf("Inside multByTwo with arg %d on thread %s%n",
                n, Thread.currentThread().getName());
        if (n == 101) try {
            throw new Exception("101 is a bad number");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return n * 2;
    }

    public static boolean modByThree(int n) {
        System.out.printf("Inside modByThree with arg %d on thread %s%n", n,
                Thread.currentThread().getName());
        return n % 3 == 0;
    }

    public static void main(String[] args) {
        // multiply numbers between 100 and 200 by 2, then find first n divisible by 3
        int firstEvenDoubleDivBy3 = IntStream.rangeClosed(100, 200)
                .map(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .findFirst().orElse(0);
        System.out.println(firstEvenDoubleDivBy3);


        // Demonstrate laziness using print statements
        firstEvenDoubleDivBy3 = IntStream.rangeClosed(100, 2_000_000)
                // .parallel()
                .map(n -> multByTwo(n))
                .filter(LazyStreams::modByThree)
                .findFirst().orElse(0);
        System.out.printf("First even divisible by 3 is %d%n", firstEvenDoubleDivBy3);
    }
}
