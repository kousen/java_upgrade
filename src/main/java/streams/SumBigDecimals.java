package streams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class SumBigDecimals {

    public BigDecimal sumFirstN_asDoubles(int n) {
        double total = Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
                .limit(n)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        return new BigDecimal(total + "");
    }

    public BigDecimal sumFirstN_usingReduce(int n) {
        return Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
                .limit(n)
                .reduce((accumulator, eachBigDecimal) -> {
                    System.out.println("accumulator = " + accumulator +
                            ", eachBigDecimal = " + eachBigDecimal);
                    return accumulator.add(eachBigDecimal);
                })
                .orElse(BigDecimal.ZERO);
    }

    // Off by one error, because 1 is never doubled
    public BigDecimal sumDoubles(int n) {
        BigDecimal two = new BigDecimal("2");
        return Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
                .limit(n)
                .reduce((total, e) -> {
                    System.out.println("total = " + total + ", e = " + e);
                    return total.add(e.multiply(two));
                }).orElse(BigDecimal.ZERO);
    }

    public BigDecimal sumDoublesInitialized(int n) {
        BigDecimal two = new BigDecimal("2");
        return Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
                .limit(n)
                .reduce(BigDecimal.ZERO, (total, e) -> {
                    System.out.println("total = " + total + ", e = " + e);
                    return total.add(e.multiply(two));
                });
    }

    public double productFirstN(int n) {
        return DoubleStream.iterate(1.0, k -> k + 1)
                .limit(n)
                .reduce(1.0, (acc, dbl) -> acc * dbl);
    }

    public double productFirstNVerbose(int n) {
        return DoubleStream.iterate(1.0, k -> k + 1)
                .limit(n)
                .reduce(1.0, (acc, dbl) -> {
                    System.out.println("acc = " + acc + ", dbl = " + dbl);
                    return acc * dbl;
                });
    }
}
