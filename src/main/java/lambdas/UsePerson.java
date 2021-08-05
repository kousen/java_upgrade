package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Old school (Java 7 or earlier)
        List<Person> beatles = new ArrayList<>(); // Shared mutable state
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        people = names.stream()
                .map(Person::new)  // constructor reference
                .collect(Collectors.toList());
        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
        System.out.println(Arrays.toString(peopleArray));
    }
}
