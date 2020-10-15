package streams;

import java.util.Arrays;
import java.util.List;

public class PeekDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a",
                "list", "of", "strings");

        strings.stream()
                .peek(x -> System.out.println("Before filter: " + x))
                .filter(s -> s.length() % 2 == 0)
                .peek(x -> System.out.println("After filter: " + x))
                .map(String::toUpperCase)
                .peek(x -> System.out.println("After map: " + x))
                .forEach(System.out::println);
    }
}
