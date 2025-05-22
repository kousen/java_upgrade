package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    public static void main(String[] args) {
        List<String> names = List.of("John", "Paul", "George", "Ringo");

        // Old-style way:
        List<Person> beatles = new ArrayList<>(); // Shared mutable state
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        people = names.stream()
                .map(Person::new) // uses the Person(String) ctr
                // .map(Person::new) // uses the Person(Person) ctr
                .collect(Collectors.toList());
        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
                //.toArray(value -> new Person[value]);
        System.out.println(Arrays.toString(peopleArray));

        List<String> fullNames = List.of(
                "John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr");
        people = fullNames.stream()
                .map(name -> name.split(" "))
                .map(Person::new) // use the Person(String...) ctr
                .collect(Collectors.toList());
        System.out.println(people);
        System.out.println(people.getClass().getName());

        // p1..p5 | p6..p10 | p11..p15 | p16..p20  // say you have 4 cores and run in parallel
        //   l1       l2         l3         l4
        //                 list
        LinkedList<Person> linkedPersons = names.stream()
                .map(Person::new)
                .collect(
                        () -> new LinkedList<Person>(),          // Supplier<LinkedList>
                        (list, person) -> list.add(person),      // BiConsumer<LinkedList, Person>
                        (list1, list2) -> list1.addAll(list2));  // BiConsumer<LinkedList, LinkedList>
        System.out.println(linkedPersons);

        linkedPersons = names.stream()
                .map(Person::new)
                .collect(
                        LinkedList::new,      // Supplier<LinkedList>
                        LinkedList::add,      // BiConsumer<LinkedList, Person>
                        LinkedList::addAll);  // BiConsumer<LinkedList, LinkedList>
        System.out.println(linkedPersons);

        linkedPersons = names.stream()
                .map(Person::new)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(linkedPersons);
    }
}
