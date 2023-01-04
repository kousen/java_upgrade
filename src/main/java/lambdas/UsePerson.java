package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Old style
        List<Person> beatles = new ArrayList<>();  // shared mutable state
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // New style, using streams, lambdas, and method references
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        people = names.stream()
                .map(Person::new) // invokes the Person(String) constructor
                .collect(Collectors.toList());
        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
        System.out.println(Arrays.toString(peopleArray));

        names = Arrays.asList("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr");
        people = names.stream()
                .map(s -> s.split(" "))
                .map(Person::new)  // invokes the Person(String...) constructor
                .map(Person::new)  // invokes the Person(Person) constructor
                .collect(Collectors.toList());
        System.out.println(people);

        // If we are in parallel, with 4 cores:  Uses a fork-join pool to the concurrent work
        //  1..25 | 26..50 | 51..75 | 76..100
        //   LL1  |  LL2   |  LL3   |   LL4
        //              LL_total
        people = names.stream()
                .map(s -> s.split(" "))
                .map(Person::new)
                .peek(person -> System.out.println("Thread: " + Thread.currentThread().getName()
                        + " Person: " + person))
                .collect(LinkedList::new,     // Supplier<LinkedList>
                        LinkedList::add,      // add a single Person to the LinkedList
                        LinkedList::addAll);  // combine two LinkedLists
        System.out.println(people);

        // As lambdas:
        people = names.parallelStream()
                .map(s -> s.split(" "))
                .map(Person::new)
                .peek(person -> System.out.println("Thread: " + Thread.currentThread().getName()
                        + " Person: " + person))
                .collect(() -> new LinkedList<>(),                   // Supplier<LinkedList>
                        (people1, person) -> people1.add(person),    // add a single Person to the LinkedList
                        (total, peopleN) -> total.addAll(peopleN));  // combine two LinkedLists
        System.out.println(people);
    }
}
