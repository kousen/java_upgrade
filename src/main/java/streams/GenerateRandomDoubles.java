package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class GenerateRandomDoubles {
    public static void main(String[] args) {
        DoubleStream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        ArrayList<Double> randoms = DoubleStream.generate(Math::random)
                .limit(10)
                .collect(ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);
        System.out.println(randoms);

        List<Double> randomDoubles = DoubleStream.generate(Math::random)
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(randomDoubles);
    }
}
