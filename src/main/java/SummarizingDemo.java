import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;

public class SummarizingDemo {
    public static void main(String[] args) {
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .summaryStatistics();

        System.out.println(stats);
        System.out.println("Count: " + stats.getCount());
        System.out.println("Min  : " + stats.getMin());
        System.out.println("Ave  : " + stats.getAverage());
        System.out.println("Max  : " + stats.getMax());
        System.out.println("Min  : " + stats.getSum());
    }
}
