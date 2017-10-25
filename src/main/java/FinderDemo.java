import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class FinderDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

        Optional<String> first = strings.stream()
                .filter(s -> s.length() == 5)
                .findFirst();
        System.out.println(first);

        IntStream.rangeClosed(1, 7)
                .forEach(len ->
                        System.out.println(len + ": " +
                                strings.stream()
                                .filter(s -> s.length() == len)
                                .findFirst().orElse("NONE")));

        IntStream.rangeClosed(1, 7)
                .forEach(len ->
                    strings.stream()
                        .filter(s -> s.length() == len)
                        .findFirst().ifPresent(s -> System.out.println(len + ": " + s)));
    }
}
