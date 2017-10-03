import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomDemo {
    public static void main(String[] args) {
        Random r = new Random();
        int sum = IntStream.generate(() -> r.nextInt(10))
                .limit(10)
                .map(n -> {
                    System.out.println("n = " + n);
                    return n;
                })
                .filter(n -> n % 2 == 0)  // only even numbers
                .peek(System.out::println)
                .map(n -> n * 2)
                .sum();
        System.out.println(sum);

        System.out.println("Filtering integers:");
        OptionalInt first = IntStream.generate(() -> r.nextInt(10))
                .peek(System.out::println)
                .filter(n -> n > 7)
                .findFirst();

        List<Integer> collect = IntStream.range(1, 10)
                .filter(n -> n % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(collect);

        collect = IntStream.range(1, 10)
                .filter(n -> n % 2 == 0)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(collect);

        int[] ints = IntStream.range(1, 10)
                .filter(n -> n % 2 == 0)
                .toArray();
        Arrays.stream(ints)
                .forEach(System.out::println);
        System.out.println(Arrays.toString(ints));

    }
}
