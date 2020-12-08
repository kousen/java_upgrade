package streams;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class GenerateRandomNumbers {
    public static void main(String[] args) {
        List<Double> doubles = DoubleStream.generate(Math::random)
                .limit(100)  // DoubleStream
                .collect(ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);
        System.out.println(doubles);

        doubles = DoubleStream.generate(Math::random)
                .limit(100)
                .boxed()  // Stream<Double>
                .collect(Collectors.toList());
        System.out.println(doubles);

        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(100_000)
                .summaryStatistics();
        System.out.println(stats);
        System.out.println(stats.getCount());
    }
}
