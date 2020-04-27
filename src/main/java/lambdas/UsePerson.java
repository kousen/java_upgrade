package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        List<Person> peopleWithEvenLengthNames = new ArrayList<>();
        for (String n : names) {
            if (n.length() % 2 == 0) {
                peopleWithEvenLengthNames.add(new Person(n));
            }
        }
        System.out.println(peopleWithEvenLengthNames);

        List<Person> people = names.stream()    // Stream<String>
                .filter(name -> name.length() % 2 == 0)
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        people = names.stream()
                .map(Person::new)  // invokes the Person(String) ctor
                .collect(Collectors.toList());

        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);

        System.out.println(Arrays.toString(peopleArray));
    }
}
