package lambdas;

import java.util.Arrays;
import java.util.List;

public class IterableDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Pat", "Ken", "Indukumar", "Mark", "Caspar");

        // Traditional foreach loop
        for (String name : names) {
            System.out.println(name);
        }

        // New default forEach method in Iterable
        names.forEach(s -> System.out.println("The current name is " + s));

        // Method reference for the print
        names.forEach(System.out::println);
    }
}
