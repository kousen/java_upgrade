package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Java 7 or below:
        List<Person> persons = new ArrayList<>();  // Shared mutable state
        for (String name : names) {
            persons.add(new Person(name));
        }
        System.out.println(persons);

        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>

        System.out.println(people);

        Stream<Person> personStream = names.stream()
                .map(name -> new Person(name));// no data is actually processed
        System.out.println(personStream);

        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());

        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);

        System.out.println(Arrays.asList(peopleArray));
    }
}
