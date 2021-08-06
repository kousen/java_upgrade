package streams;

import java.util.Arrays;

public class PeekDemo {
    public static void main(String[] args) {
        int total = Arrays.stream("this is a stream of strings".split("\\s+"))
                .peek(s -> System.out.println("Before the even filter: " + s))  // debugging
                .filter(s -> s.length() % 2 == 0)
                .peek(s -> System.out.println("After the even filter: " + s))
                .mapToInt(String::length)
                .peek(s -> System.out.println("After converting to lengths: " + s))
                .sum();
        System.out.println("The total length of the even-length strings is " + total);
    }
}
