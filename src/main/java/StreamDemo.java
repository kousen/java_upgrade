import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

        Stream<String> stringStream = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .map(String::toUpperCase);

        System.out.println(stringStream.collect(Collectors.toList()));

        // stringStream.count();

        Optional<String> min = strings.stream()
                .min(Comparator.comparingInt(String::length));
        System.out.println(min);

        double average = Stream.generate(Math::random)
                .limit(1_000_000)
                .mapToDouble(Double::doubleValue)
                .average().orElse(0.0);
        System.out.println(average);

        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .summaryStatistics();
        System.out.println(stats);
        System.out.println(stats.getCount());
        System.out.println(stats.getAverage());
        System.out.println(stats.getMax());
        System.out.println(stats.getMin());
        System.out.println(stats.getSum());
    }
}
