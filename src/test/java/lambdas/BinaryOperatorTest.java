package lambdas;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryOperatorTest {
    @Test
    public void concatAsBinaryOperator() {
        BinaryOperator<String> concat = String::concat;
        // concat = (s, str) -> s.concat(str);

        List<String> strings = List.of("this", "is", "a", "list", "of", "strings");
        Optional<String> str = strings.stream()
                //.filter(s -> false)
                .reduce(concat);
        System.out.println(str.orElse(""));
        
        // Assert that the reduction produces expected result
        assertEquals("thisisalistofstrings", str.orElse(""));
    }
}
