package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Classic, Java 7 approach:
        List<Person> beatles = new ArrayList<>(); // shared mutable state
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // Using a (sequential) stream:
        long start = System.nanoTime();
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        long end = System.nanoTime();
        System.out.println(people);
        System.out.println("Sequential: " + (end - start) + "ns");

        // Method (constructor) reference:
        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(people);

        // In parallel (not worth it in this particular case):
        start = System.nanoTime();
        people = names.parallelStream()
                .map(Person::new)
                .collect(Collectors.toList());
        end = System.nanoTime();
        System.out.println("Parallel: " + (end - start) + "ns");
        System.out.println(people);

        // Convert to an array instead of a collection:
        Person[] peopleArray = names.stream()
                .map(Person::new) // Constructor ref for Person
                .toArray(Person[]::new);  // Constructor ref for Person[]
        System.out.println(Arrays.toString(peopleArray));
    }
}
