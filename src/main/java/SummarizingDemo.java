import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;

public class SummarizingDemo {

    public static DoubleStream getLimitedRandomStream(int limit) {
        return DoubleStream.generate(Math::random).limit(limit);
    }

    public static void main(String[] args) {
        DoubleStream doubleStream = getLimitedRandomStream(10);

        doubleStream.forEach(System.out::println);
        // doubleStream.forEach(System.out::println);

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
