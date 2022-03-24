package lazy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LazyStreams {
    private static final Logger logger = Logger.getLogger(LazyStreams.class.getName());

    public static int multByTwo(int n) {
        System.out.printf("Inside multByTwo with arg %d and thread %s%n", n, Thread.currentThread().getName());
        return n * 2;
    }

    public static boolean modByThree(int n) {
        System.out.printf("Inside modByThree with arg %d and thread %s%n", n, Thread.currentThread().getName());
        return n % 3 == 0;
    }

    public static void main(String[] args) {
        // multiply numbers between 100 and 200 by 2, then find first n divisible by 3
        int firstEvenDoubleDivBy3 = IntStream.range(100, 200)
                .filter(n -> n % 3 == 0)
                .map(n -> n * 2)
                .findFirst().orElse(0);
        System.out.println(firstEvenDoubleDivBy3);


        // Demonstrate laziness using print statements
        firstEvenDoubleDivBy3 = IntStream.range(100, 2_000_000)
                .filter(LazyStreams::modByThree)
                .map(LazyStreams::multByTwo)
                .findAny().orElse(0);
        System.out.printf("First even divisible by 3 is %d%n", firstEvenDoubleDivBy3);

        List<Integer> numbers = IntStream.range(1, 100)
                // .mapToObj(i -> Integer.valueOf(i))
                .boxed()
                .parallel()
                .collect(Collectors.toList());
        System.out.println(numbers);

        List<Integer> ints = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(1, 100)
                .parallel()
                .forEach(ints::add);
        System.out.println(ints);
        System.out.println(ints.size());

        int total = 0;  // can NOT change a local variable inside a lambda
//        ints.stream()
//                .forEach(n -> total += n);
        total = ints.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(total);
    }
}
