package streams;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HigherOrderFunction {
    static Function<String, String> f() {
        System.out.println("f");
        return String::toUpperCase;
    }

    public static void main(String[] args) {
        List<String> list = Stream.of("a", "b", "c")
                .map(f())
                .collect(Collectors.toList());
        System.out.println(list);
    }
}
