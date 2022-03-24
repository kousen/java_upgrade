package streams;

import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;

public class SummaryStats {
    public static void main(String[] args) {
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(10_000_000)
                .summaryStatistics();
        System.out.println(stats);
    }
}
