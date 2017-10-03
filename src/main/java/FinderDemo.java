import java.util.*;

public class FinderDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

        Optional<String> first = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .sorted()
                .findFirst();
        System.out.println(first);
        first.ifPresent(s -> System.out.println("The contained string is " + s));

        first = strings.stream()
                .filter(s -> s.length() > 10)
                .findFirst();
        System.out.println(first);
        first.ifPresent(System.out::println);

        String str = strings.stream()
                .filter(s -> s.length() % 10 == 0)
                .findFirst().orElse("[none]");
        System.out.println("The returned string is " + str);

        String max = strings.stream()
                // .filter(s -> s.length() > 10)
                .max(Comparator.comparingInt(String::length)).orElse("(no elements in stream)");
        System.out.println("The max element is " + max);

    }
}
