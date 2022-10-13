package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // "Old school" way (up to Java 1.7)
        List<Person> beatles = new ArrayList<>(); // shared mutable state
        // functional program favors immutability (which we're not doing here)
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // Java 8 and above way
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
                .toArray(Person[]::new); // array constructor reference
        System.out.println(Arrays.toString(peopleArray));
    }
}
