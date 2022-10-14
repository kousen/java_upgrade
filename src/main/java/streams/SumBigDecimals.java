package streams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal sumFirstN_usingReduce_verbose(int n) {
        return Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
                .limit(n)
                .reduce((total, element) -> {
                    System.out.println("total = " + total + ", element = " + element);
                    return total.add(element);
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

    // 1 2 3 4 | 5 6 7 8 | 9 10 11 12 | 13 14 15 16
    //    L1        L2         L3           L4
    //                 L_total
    public List<BigDecimal> reduceStream(int n) {
        // Using lambdas with 3-arg collect
//        return Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
//                .limit(n)
//                .parallel()
//                .collect(() -> new ArrayList<>(),              // supplier
//                        (list, element) -> list.add(element),  // accumulator
//                        (list1, list2) -> list1.addAll(list2)  // combiner
//                );

        // Using method references with 3-arg collect
        return Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
                .limit(n)
                .parallel()
                .collect(ArrayList::new,   // supplier
                        ArrayList::add,    // accumulator
                        ArrayList::addAll  // combiner
                );
    }
}
