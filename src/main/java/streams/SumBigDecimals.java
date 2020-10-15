package streams;

import java.math.BigDecimal;
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
                .reduce((accumulator, current) -> {
                    System.out.println("Accumulator: " + accumulator + ", Current: " + current);
                    return accumulator.add(current);
                }).orElse(BigDecimal.ZERO);
    }

    // Off by one error, because 1 is never doubled
    public BigDecimal sumDoubles(int n) {
        BigDecimal two = new BigDecimal("2");
        return Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
                .limit(n)
                .reduce((total, each) -> {
                    System.out.println("total = " + total + ", each = " + each);
                    return total.add(each.multiply(two));
                }).orElse(BigDecimal.ZERO);
    }

    public BigDecimal sumDoublesInitialized(int n) {
        BigDecimal two = new BigDecimal("2");
        return Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
                .limit(n)
                .reduce(BigDecimal.ZERO, (total, each) -> {
                    System.out.println("total = " + total + ", each = " + each);
                    return total.add(each.multiply(two));
                });
    }
}
