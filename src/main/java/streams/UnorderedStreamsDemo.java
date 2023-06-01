package streams;

import java.util.Arrays;
import java.util.List;

public class UnorderedStreamsDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.stream()
                .unordered()
                .parallel()
                .forEach(System.out::println);
    }
}
