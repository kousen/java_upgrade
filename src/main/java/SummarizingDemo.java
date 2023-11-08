import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;

public class SummarizingDemo {
    public static void main(String[] args) {
        DoubleStream.generate(Math::random)
                .limit(10)             // intermediate operation
                .forEach(System.out::println); // terminal operation

        // Poor (maybe destitute) developer's statistics package
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .summaryStatistics();

        System.out.println("toString(): " + stats);
        System.out.println("Count     : " + stats.getCount());
        System.out.println("Min       : " + stats.getMin());
        System.out.println("Average   : " + stats.getAverage());
        System.out.println("Max       : " + stats.getMax());
        System.out.println("Sum       : " + stats.getSum());
    }
}
