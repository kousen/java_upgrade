package streams;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class SummaryStatistics {
    public static void main(String[] args) {
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .summaryStatistics();
        System.out.println(stats);

        System.out.println("Min: " + stats.getMin());
        System.out.println("Ave: " + stats.getAverage());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());

        OptionalDouble max = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .max();
        System.out.println("The max value is " + max);

        List<Double> doubles = DoubleStream.generate(Math::random)
                .limit(1000)
                // .mapToObj(d -> Double.valueOf(d))
                .boxed()
                .collect(Collectors.toList());
    }
}
