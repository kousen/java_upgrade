package streams;

import java.util.stream.IntStream;

public class ReduceProduct {
    public static void main(String[] args) {
        int product = IntStream.rangeClosed(1, 5)
                .reduce((acc, n) -> acc * n).orElse(0);
        System.out.println("The product of the first 5 numbers is " + product);

        product = IntStream.rangeClosed(1, 5)
                .reduce((acc, n) -> {
                    System.out.println("acc = " + acc + ", n = " +n);
                    return acc * n;
                }).orElse(0);
        System.out.println(product);

        int productOfDoubles = IntStream.rangeClosed(1, 5)
                .reduce(1, (acc, n) -> {
                    System.out.println("acc = " + acc + ", n = " +n);
                    return acc * 2 * n;
                });
        System.out.println(productOfDoubles);
    }
}
