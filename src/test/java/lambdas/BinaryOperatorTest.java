package lambdas;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class BinaryOperatorTest {
    @Test
    public void concatAsBinaryOperator() {
        BinaryOperator<String> concat = String::concat;
        concat = (s, str) -> s.concat(str);

        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        Optional<String> str = strings.stream()
                //.filter(s -> false)
                .reduce(concat);
        System.out.println(str.orElse(""));
    }
}
