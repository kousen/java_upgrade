import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomDemo {
    public static void main(String[] args) {
        Random r = new Random();
        int sum = IntStream.generate(() -> r.nextInt(10))
                .limit(10)
                .filter(n -> n % 2 == 0)
                .peek(System.out::println)
                .map(n -> n * 2)
                .sum();
        System.out.println(sum);

        System.out.println("Filtering integers:");
        OptionalInt first = IntStream.generate(() -> r.nextInt(10))
                .peek(System.out::println)
                .filter(n -> n > 7)
                .findFirst();
        first.ifPresent(n -> System.out.println("First > 7: " + n));
    }
}
