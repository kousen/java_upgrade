import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.DoubleStream;

public class SummarizingDemo {
    public static void main(String[] args) {
        List<Double> collect = DoubleStream.generate(Math::random)
                .limit(10)
                .boxed()
                .toList();
        System.out.println(collect);


        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .summaryStatistics();

        System.out.println(stats);
        System.out.println(stats.getCount());
        System.out.println(stats.getMin());
        System.out.println(stats.getAverage());
        System.out.println(stats.getMax());
        System.out.println(stats.getSum());
    }
}
