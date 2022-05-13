package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr");

        // Old style
        List<Person> beatles = new ArrayList<>();  // shared mutable state
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        people = names.stream()
                .map(Person::new) // Person(String)
                .collect(Collectors.toList());
        System.out.println(people);

        people = names.stream()
                .map(name -> name.split(" "))
                .map(Person::new) // Person(String... names)
                .map(Person::new) // Person(Person person)
                .collect(Collectors.toList());
        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);

        System.out.println(Arrays.toString(peopleArray));
    }
}
