package uberconf;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Created by Ken Kousen on 7/19/16.
 */
public class Stats {
    public static void main(String[] args) {
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .summaryStatistics();

        System.out.println(stats);

        Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                .limit(10)
                .forEach(n -> System.out.println(n + " " +
                        n.getClass().getName()));
    }
}
