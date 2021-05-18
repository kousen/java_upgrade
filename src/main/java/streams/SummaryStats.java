package streams;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class SummaryStats {
    public static void main(String[] args) {
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(100)
                .summaryStatistics();
        System.out.println(stats);
        System.out.println(stats.getMax());
        System.out.println(stats.getMin());
        System.out.println(stats.getAverage());
        System.out.println(stats.getCount());
        System.out.println(stats.getSum());

        List<Double> doubles = DoubleStream.generate(Math::random)
                .limit(10)
                .collect(ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);
        System.out.println(doubles);

        doubles = DoubleStream.generate(Math::random)  // DoubleStream
                .limit(10)
                //.mapToObj(d -> Double.valueOf(d))  // convert to Stream<Double>
                .boxed()  // convert to Stream<Double>
                .collect(Collectors.toList());
        System.out.println(doubles);
    }
}
