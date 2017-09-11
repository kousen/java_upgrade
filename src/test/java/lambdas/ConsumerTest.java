package lambdas;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {
    @Test
    public void iterateOverStrings() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

        for (String s : strings) {
            System.out.println(s);
        }

        // anonymous inner class
        strings.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        // expression lambda with type declaration
        strings.forEach((String s) -> System.out.println(s));

        // expression lambda without type declaration
        strings.forEach(s -> System.out.println("The string is " + s));

        // block lambda
        strings.forEach((String s) -> {
            System.out.println(s);
        });

        // method reference
        strings.forEach(System.out::println);
    }
}
