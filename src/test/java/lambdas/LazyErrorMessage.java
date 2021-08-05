package lambdas;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyErrorMessage {
    private final Logger log = Logger.getLogger("Logger");

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "False is not true";
    }

    @Test
    void showError() {
        // assertTrue(true, () -> getErrorMessage());
        assertTrue(true, this::getErrorMessage);
        log.fine(() -> "Error messsage");  // Creates the log message only if it will be seen
    }

    @Test
    void effectivelyFinal() {
        int total = 0;  // "effectively" final
//        Stream.of(3, 1, 4, 1, 5, 9)
//                .forEach(n -> total += n);  // same rule as with anon inner classes
        total = IntStream.of(3, 1, 4, 1, 5, 9).sum();
    }

    @Test
    void NPE() {
        String str = null;
        assertThrows(NullPointerException.class, () -> str.length());
        new Thread(() -> dealWithIOException("-1"));
    }

    private void dealWithIOException(String arg) {
        try {
            throw new IOException(arg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
