package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Java 7 way to convert a list of string into a list of Person
        List<Person> beatles = new ArrayList<>(); // shared mutable state (hard for concurrent operations)
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
        System.out.println(Arrays.toString(peopleArray));

        // Sum the lengths of the names of the people
        int totalLength = names.parallelStream()
                .mapToInt(name -> name.length())
                .sum();
        System.out.println("The total length of the names is " + totalLength);

        // Without using an IntStream
        // local variables can be read in lambdas, but not modified
        // they must be final or "effectively final"
//        totalLength = 0;
//        names.stream()
//                .map(name -> name.length())
//                .forEach(length -> totalLength += length);

        // "Pure" function --> (1) output is determined only by the inputs
        //                     (2) function has no side effects
        // Functional programming favors pure functions --> easy to reason about and predict
        // Functional programming favors immutability
    }
}
