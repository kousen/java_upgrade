import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class FinderDemo {
    public static void main(String[] args) {
        List<String> strings = List.of("this", "is", "a", "list", "of", "strings");
        HashSet<String> stringSet = new HashSet<>(strings);

        Optional<String> first = strings.stream()
                .filter(s -> {
                    System.out.println(Thread.currentThread().getName() + " with " + s);
                    return s.length() == 2;
                })
                .findFirst();
        System.out.println(first);
    }
}
