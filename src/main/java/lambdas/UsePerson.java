package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        // Java 9+: List<String> names = List.of("John", "Paul", "George", "Ringo");
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // In Java 7:
        List<Person> beatles = new ArrayList<>();
        for (String name: names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // Uses lambda with a streqm
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        // Uses constructor ref with a stream
        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(people);

        // Produces an array with a ctor ref and an array ctor ref
        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
        System.out.println(Arrays.toString(peopleArray));
    }
}
