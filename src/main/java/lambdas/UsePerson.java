package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr");

        // Java 7 and earlier
        List<Person> beatles = new ArrayList<>(); // Shared mutable state
        for (String name : names) {
            if (name.length() > 4) {
                beatles.add(new Person(name));
            }
        }
        System.out.println(beatles);

        // Java Streams
        List<Person> people = names.stream()    // Stream<String>
                .filter(name -> name.length() > 4) // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        people = names.stream()
                .map(name -> name.split(" ")) // Stream<String[]>
                .map(Person::new) // calls new Person(String...)
                .collect(Collectors.toList());
        System.out.println(people);

        people = names.stream()
                .map(Person::new) // calls new Person(String)  Stream<Person>
                .map(Person::new) // calls new Person(Person)
                .collect(Collectors.toList());
        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
        System.out.println(Arrays.toString(peopleArray));
    }
}
