import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsDemo {
    public static void main(String[] args) {
        List<Integer> nums = Stream.of("this", "is", "a", "collection", "of", "strings")
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
                .collect(Collectors.toList());
        System.out.println(nums);

        Set<String> set = Stream.of("this is a is a collection of strings".split(" "))
                .collect(Collectors.toSet());
        System.out.println(set);
        System.out.println(set.getClass().getName());

        Set<String> collection =
                Stream.of("this is a is a collection of strings".split(" "))
                .collect(Collectors.toCollection(ConcurrentSkipListSet::new));

        Map<String, Integer> stringMap =
                Stream.of("this is a collection of strings".split(" "))
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(stringMap);
    }
}
