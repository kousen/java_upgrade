package streams;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SumBigDecimals {

    public int sumFirstNIntegers(int n) {
        //return IntStream.rangeClosed(1, n).sum();
        return Stream.iterate(0, x -> x + 1)
                .limit(n + 1)
                .reduce(0, Integer::sum);
    }

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
                .reduce((accumulator, value) -> {
                    System.out.println("accumulator = " + accumulator + ", value = " + value);
                    return accumulator.add(value);
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
                })
                .orElse(BigDecimal.ZERO);
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

    public BigDecimal productFirstN(int n) {
        return Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
                .limit(n)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
    }
}
