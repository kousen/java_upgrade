package lambdas;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SimpleDemo {
    public static boolean isPalindrome(String s) {
        String forward = s.toLowerCase().codePoints()
                // .parallel() // optional
                .filter(Character::isLetterOrDigit)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();

        String backward = new StringBuilder(forward).reverse().toString();
        return forward.equals(backward);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("Madam, in Eden, I'm Adam"));

        Consumer<Integer> printer = System.out::println;
        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(printer);


        List<String> values = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(e -> {
                    System.out.println("Filtered inside map: " + e);
                    return e;
                })
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        System.out.println(values);

        Stream.of('a', 'e', 'i', 'o', 'u')
                .map(ch -> (char) (ch + 1))
                .forEach(System.out::println);

        Stream.of('a', 'e', 'i', 'o', 'u')
                .forEach(x -> System.out.println((char) (x + 1)));

        int max =
                Stream.of("this", "is", "a", "stream", "of", "strings") // Stream<String>
                        .mapToInt(String::length)  // IntStream
                        // .filter(n -> n > 10)
                        .max().orElseGet(() -> 0);
        System.out.println(max);

        IntStream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)  // IntStream
                .boxed()                         // Stream<Integer>
                .collect(Collectors.toList());

    }
}
