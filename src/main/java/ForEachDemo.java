import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ForEachDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(s -> System.out.println("the current string is " + s));
        strings.forEach(System.out::println); // instance method (println) on an object (System.out)
                   // each element is the argument to the method

        strings.stream()  // Stream<String>
                // .map(s -> s.length())
                .sorted(Comparator.comparing(String::length)) // produce a Stream of sorted by length Strings
                .map(String::length)  // instance method (length()) on a class (String)
                                      // each element is the *target* of the method
                                      // Stream<Integer>
                .forEach(System.out::println);
    }
}
