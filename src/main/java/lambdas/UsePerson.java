package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Java 7 world: make a list of Person, loop over names, call ctor, add to list
        List<Person> beatles = new ArrayList<>();
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // Java 8+ streams
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        // Use a constructor reference to instantiate the Person
        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(people);

        // Convert in parallel (highly inefficient!)
        people = names.parallelStream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(people);

        // Constructor ref for Person and for Person[]
        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
        System.out.println(Arrays.toString(peopleArray));
    }
}
