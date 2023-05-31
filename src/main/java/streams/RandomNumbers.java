package streams;

import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class RandomNumbers {
    public static void main(String[] args) {
        System.out.println(DoubleStream.generate(Math::random)
                .limit(10_000));


        DoubleSummaryStatistics statistics = DoubleStream.generate(Math::random)
                .limit(10_000)
                .summaryStatistics();
        System.out.println(statistics);

        IntStream.iterate(1, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);
    }
}
