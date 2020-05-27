package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Local, shared mutable state, sequential
        List<Person> persons = new ArrayList<>();
        for (String name : names) {
            if (name.length() % 2 == 0) {
                persons.add(new Person(name));
            }
        }
        System.out.println(persons);

        List<Person> people = names.stream()    // Stream<String>
                .filter(name -> name.length() % 2 == 0)
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>


        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());

        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);

        System.out.println(Arrays.toString(peopleArray));
    }
}
