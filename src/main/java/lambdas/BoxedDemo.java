package lambdas;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BoxedDemo {
    public static void main(String[] args) {
        String[] strings = "this is a list of strings".split("\\s+");

        List<String> collect = Arrays.stream(strings)
                                     .collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> lengths = Arrays.stream(strings)
                                       .map(String::length) // Stream<Integer>
                                       .collect(Collectors.toList());

        List<Integer> wrappedLengths = Arrays.stream(strings)
                                       .mapToInt(String::length)  // IntStream
                                       .boxed()                   // Stream<Integer>
                                       .collect(Collectors.toList());// no good on primitive streams

        LinkedList<Object> moreLengths = Arrays.stream(strings)
                                            .mapToInt(String::length)
                                            .collect(LinkedList::new,
                                                     LinkedList::add,
                                                     LinkedList::addAll);
    }
}
