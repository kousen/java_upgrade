import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CollectionsDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(CollectionsDemo.class.getName());

        List<String> strings = Arrays.asList("this is a list of strings".split(" "));

        List<String> evens = strings.stream()
                .map(s -> {
                    logger.info("Before the filter: " + s);
                    return s;
                })
                .filter(s -> s.length() % 2 == 0)
                .peek(s -> logger.info(() -> "After the filter: " + s))
                .collect(Collectors.toList());

        System.out.println(evens);
    }
}
