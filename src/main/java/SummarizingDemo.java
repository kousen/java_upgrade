import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;

public class SummarizingDemo {
    public static void main(String[] args) {
        // Prints the memory address of the resulting stream
        DoubleStream stream = DoubleStream.generate(Math::random)
                .limit(10_000_000);
        System.out.println(stream);

        // Prints the values in the stream
        System.out.println(
                "The sum of 10_000_000 random doubles is " + stream.sum());

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
