package streams;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ExceptionTest {
    private final List<String> strings = Arrays.asList("this", "is", "a",
            "list", "of", "strings");

    private String checkLengthAndThrowException(String s) {
        if (s.length() > 5) {
            throw new RuntimeException("String too long");
        }
        return s;
    }

    public String callValidation(String s) {
        try {
            return checkLengthAndThrowException(s);
        } catch (RuntimeException e) {
            throw new RuntimeException("String too long");
        }
    }

    // To deal with exceptions inside a stream:
    // 1. Use a try-catch block inside the lambda -->
    //    you want your stream pipeline to be a single line per method call
    // 2. Delegate to a method that includes the try-catch -->
    //    preferred mechanism
    // 3. Use a framework that already catches the exception
    @Test @Disabled
    void throwRuntimeException() {
        strings.stream()
                .map(this::callValidation)
                .forEach(System.out::println);
    }
}
