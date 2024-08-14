package streams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class RandomDoubles {
    public static void main(String[] args) {
        List<Double> numbers = DoubleStream.generate(Math::random)
                .limit(10)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(numbers);

        numbers = DoubleStream.iterate(1, i -> i + 1)
                .limit(10)
                .boxed()
                .toList();
        System.out.println(numbers);

        BigDecimal sum = Stream.iterate(BigDecimal.ONE, i -> i.add(BigDecimal.ONE))
                .limit(10)
                .reduce((total, element) -> {
                    System.out.println("Total: " + total + ", Element: " + element);
                    return total.add(element);
                }).orElse(BigDecimal.ZERO);
        System.out.println(sum);
    }
}
