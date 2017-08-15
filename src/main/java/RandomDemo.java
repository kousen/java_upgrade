import java.util.OptionalInt;
import java.util.Random;
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

        sum = r.ints(10)
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .sum();
        System.out.println(sum);

        System.out.println("Filtering integers:");
        OptionalInt first = IntStream.generate(() -> r.nextInt(10))
                .peek(System.out::println)
                .filter(n -> n > 7)
                .findFirst();

    }
}
