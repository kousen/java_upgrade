package streams;

import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;

public class RandomNumbers {
    public static void main(String[] args) {
        DoubleSummaryStatistics statistics = DoubleStream.generate(Math::random)
                .limit(10_000)
                .summaryStatistics();
        System.out.println(statistics);
    }
}
