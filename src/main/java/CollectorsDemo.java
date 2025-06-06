import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsDemo {
    public static void main(String[] args) {
        var nums = Stream.of("this", "is", "a", "collection", "of", "strings")
                .parallel()
                .map(String::length)
//                .map(n -> {
//                    System.out.println(n);
//                    return n;
//                })
                // equivalent to:
                .peek(n -> {
                    System.out.println("On " + Thread.currentThread().getName()
                            + ", the value of n before the filter is " + n);
                })
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("On " + Thread.currentThread().getName()
                        + ", the value of n after the filter is  " + n))
                .toList();
        System.out.println(nums);

        var set = Stream.of("this is a is a collection of strings".split(" "))
                .collect(Collectors.toSet());
        System.out.println(set);
        System.out.println(set.getClass().getName());

        var collection =
                Stream.of("this is a is a collection of strings".split(" "))
                .collect(Collectors.toCollection(ConcurrentSkipListSet::new));

        var stringMap =
                Stream.of("this is a collection of strings".split(" "))
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(stringMap);
    }
}
