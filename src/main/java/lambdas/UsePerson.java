package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Java 7 and earlier:
        List<Person> beatles = new ArrayList<>();  // shared mutable state
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // Java 8 and above:
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        // Constructor reference:
        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
        System.out.println(Arrays.toString(peopleArray));

        List<Person> collect = names.stream()
                .map(Person::new) // invokes the Person(String) constructor
                .map(Person::new) // invokes the Person(Person) constructor
                .collect(Collectors.toList());
        System.out.println(collect);

        collect = names.parallelStream()
                .map(Person::new)
                .collect(Collectors.toList());  // preserves order
        System.out.println(collect);

        List<Person> persons = Collections.synchronizedList(new ArrayList<>());
        names.parallelStream()
                .peek(x -> System.out.println(" processing name " + x +
                                              " on thread " + Thread.currentThread().getName()))
                .map(Person::new)
                .forEach(persons::add);  // does NOT preserve order
        System.out.println(persons);
    }
}
