import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class PeekDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(PeekDemo.class.getName());

        List<String> list = Stream.of("this", "is", "a", "list", "of", "strings")
                .filter(s -> s.length() > 2)
                .peek(s -> logger.fine(() ->"Filtered value: " + s))
                .map(String::toUpperCase)
                .peek(s -> logger.fine(() -> "Mapped value: " + s))
                .toList();
        System.out.println("Final list: " + list);
    }
}
