package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Sequential with shared mutable state -- difficult to parallelize
        List<Person> persons = new ArrayList<>();
        for (String n : names) {
            persons.add(new Person(n));
        }
        System.out.println(persons);

        // Sequential (by default), but easy to parallelize
        // No shared mutable state
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>


        people = names.stream()
                .map(Person::new)  // uses the one-arg Person(String) ctor
                .collect(Collectors.toList());

        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);  // create an array of the size == stream size

        System.out.println(Arrays.toString(peopleArray));
    }
}
