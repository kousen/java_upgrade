package streams;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTests {
    private final Logger logger = Logger.getLogger(StreamTests.class.getName());

    @Test
    void ofDemo() {
        String joinedString = Stream.of("this", "is", "a", "stream", "of", "strings")
                .filter(s -> s.length() % 2 == 0)
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));
        assertEquals("THIS,IS,STREAM,OF", joinedString);
    }

    @Test
    void generate() {
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(100)
                .summaryStatistics();
        System.out.println(stats);
    }

    @Test
    void peekAtElementsAsTheyGoBy() {
        List<String> strings = Arrays.stream("this is a list of strings".split(" "))
                .peek(s -> logger.fine("Before the filter: " + s))
                .filter(s -> s.length() % 2 == 0)
                .peek(s -> logger.fine(" After the filter: " + s))
                .map(String::toUpperCase)
                .peek(s -> logger.fine("    After the map: " + s))
                .collect(Collectors.toList());
        System.out.println(strings);
    }
}
