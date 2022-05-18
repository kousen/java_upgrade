package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr");

        // old-style (i.e., Java 7-) way
        List<Person> beatles = new ArrayList<>(); // shared mutable state
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // newer, functional way
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        // use a constructor reference to instantiate the Person class
        people = names.stream()
                .map(Person::new) // uses the one-arg constructor
                .collect(Collectors.toList());
        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new) // uses the one-arg constructor
                .toArray(Person[]::new);
        System.out.println(Arrays.toString(peopleArray));

        names.stream()
                .map(name -> name.split("\\s+")) // splits the name into words, Stream<String[]>
                .map(Person::new) // uses the vararg constructor, Stream<Person>
                .map(Person::new) // uses the copy constructor, Stream<Person>
                .forEach(System.out::println);

        // Say we had 20 strings and 4 processors
        // 1..5 | 6..10 | 11..15 | 16..20
        //  R1  |  R2   |   R3   |   R4  // uses accumulator
        //              R                // uses combiner (only needed in parallel)
        people = names.stream()
                .map(Person::new)
                .collect(
                        ArrayList::new,      // Supplier<List<Person>> (supplier)
                        ArrayList::add,      // BiConsumer<List<Person>, Person> (accumulator)
                        ArrayList::addAll    // BinaryOperator<List<Person>> (combiner)
                );
        System.out.println(people);

        people = names.stream()
                .map(Person::new)
                .collect(
                        () -> new ArrayList<>(),
                        (peopleList, person) -> peopleList.add(person),
                        (total, list) -> total.addAll(list)
                );
        System.out.println(people);
    }
}
