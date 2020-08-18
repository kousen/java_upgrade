package streams;

import org.junit.jupiter.api.Test;

import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTests {
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
}
