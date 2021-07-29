package streams;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
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

    public String joinStrings(List<String> strings) {
        return strings.stream()
                //.collect(Collectors.joining());
                // .reduce("", (accumulator, str) -> accumulator + str);
                .reduce("", String::concat);
    }
}
