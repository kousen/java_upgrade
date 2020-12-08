package streams;

import java.math.BigDecimal;
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
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public BigDecimal sumFirstN_usingReduce_verbose(int n) {
        return Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
                .limit(n)
                .reduce((accumulator, element) -> {
                    System.out.println("acc = " + accumulator + ", element = " + element);
                    return accumulator.add(element);
                }).orElse(BigDecimal.ZERO);
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

    public double productOfFirstNDoubles(int n) {
        return DoubleStream.iterate(1, dbl -> dbl + 1)
                .limit(n)
                .reduce(1.0, (acc, element) -> acc * element);
    }
}
