package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ConsumerComposition {
    public static void main(String[] args) {
        Consumer<String> printer = System.out::println;

        Logger logger = Logger.getLogger(ConsumerComposition.class.getName());
        Consumer<String> logConsumer = logger::info;

        Consumer<String> composed = printer.andThen(logConsumer);
        Stream.of("this", "is", "a", "stream", "of", "strings")
              .forEach(composed);

        List<String> letters = Arrays.asList("a", "b", "c");
        letters.forEach(printer);

        // Java 9 and above only
        // List<String> strings = List.of("this", "is", "a", "list", "of", "strings");

        List<String> strings = Arrays.asList("this", "is", "a", "list",
                                             "of", "strings");


        ToIntFunction<String> stringLength = String::length;
        List<Integer> lengths = strings.stream()
                                       .mapToInt(stringLength)
                                       .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
