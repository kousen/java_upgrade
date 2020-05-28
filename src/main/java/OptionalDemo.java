import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("ConstantConditions")
public class OptionalDemo {
    public static void main(String[] args) {
        Optional<Integer> first = Stream.of(3, 1, 4, 1, 5, 9)
                .filter(n -> n > 10)
                .findFirst();

        System.out.println(first);

        // System.out.println(first.isPresent() ? (int) first.get() : 0);

        int defaultValue = 999;
        System.out.println(first.orElse(defaultValue));
        System.out.println(first.orElseGet(() -> defaultValue));
        first.ifPresent(System.out::println);


        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        Optional<String> s = strings.stream()
                .findFirst();

        System.out.println(s.orElse("No string found; legal values are: " + strings));
        System.out.println(s.orElseGet(() -> "No string found; legal values are: " + strings));
        System.out.println(s.orElseThrow(IllegalArgumentException::new)); // default constructor
        System.out.println(s.orElseThrow(() -> new IllegalArgumentException("Not available")));
        s.ifPresent(System.out::println);
    }
}
