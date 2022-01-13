import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RandomDemo {
    public static void main(String[] args) {
        DoubleSummaryStatistics stats =
                DoubleStream.generate(Math::random)
                            .limit(100)
                            .summaryStatistics();
        System.out.println(stats);

        List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
        // sum numbers using for loop
        int total = 0;
        for (int x : integers) {
            total += x;
        }
        System.out.println("The sum is " + total);

        total = 0;
        integers.forEach(x -> {
                    // Can not modify a local variable inside a lambda
                    // total += x;
                });

        total = integers.stream()
                .mapToInt(Integer::valueOf)
                .sum();

        List<Integer> ints = new ArrayList<>();
        integers.forEach(x -> ints.add(x));  // not modifying the reference; modifying the object

        List<String> list = Stream.of("this", "is", "a", "stream")
                                     .collect(Collectors.toList());
        System.out.println(list.getClass().getName());

//        Random r = new Random();
//        int sum = IntStream.generate(() -> r.nextInt(10))
//                .limit(10)
//                .map(n -> {
//                    System.out.println("n = " + n);
//                    return n;
//                })
//                .filter(n -> n % 2 == 0)  // only even numbers
//                .peek(System.out::println)
//                .map(n -> n * 2)
//                .sum();
//        System.out.println(sum);
//
//        System.out.println("Filtering integers:");
//        OptionalInt first = IntStream.generate(() -> r.nextInt(10))
//                .peek(System.out::println)
//                .filter(n -> n > 7)
//                .findFirst();

    }
}
