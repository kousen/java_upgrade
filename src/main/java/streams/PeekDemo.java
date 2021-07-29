package streams;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PeekDemo {
    public static void main(String[] args) {
        Arrays.stream("this is a list of strings".split("\\s+"))
                .map(s -> {
                    System.out.println(s);
                    return s;
                })
                .filter(s -> s.length() % 2 == 0)
                .peek(s -> System.out.println("The current word is " + s))
                .map(String::toUpperCase)
                .forEach(System.out::println);

        IntStream.of(3, 1, 4, 1, 5, 9)
                //.mapToObj(Integer::valueOf)  // preferred over new Integer(n)
                //.mapToObj(n -> new Integer(n))
                .boxed()  // replaces mapToObj(valueOf)
                .collect(Collectors.toList());
    }
}
