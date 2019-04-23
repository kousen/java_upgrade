package lambdas;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Algorithms {
    public static BigInteger factorial(long num) {
        return num == 0 || num == 1 ? BigInteger.ONE :
                Stream.iterate(BigInteger.valueOf(2), x -> x.add(BigInteger.ONE))
                        .limit(num - 1)
                        .reduce(BigInteger.ONE,
                                BigInteger::multiply);
    }
}
