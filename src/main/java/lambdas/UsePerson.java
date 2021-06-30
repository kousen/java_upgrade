package lambdas;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        people = names.stream()
                .map(Person::new)  // constructor reference, uses Person(String) ctor
                .collect(Collectors.toList());
        System.out.println(people);
        System.out.println(people.getClass().getName());

        people = names.stream()
                .map(Person::new)  // constructor reference, uses Person(String) ctor
                //.collect(Collectors.toCollection(() -> new LinkedList<>()));
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(people);
        System.out.println(people.getClass().getName());

        // convert the list of string into an array of Person
        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new); // dimension an array of Stream size and fill it

        System.out.println(Arrays.toString(peopleArray));
    }
}
