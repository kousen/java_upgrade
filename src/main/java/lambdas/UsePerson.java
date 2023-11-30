package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings("Convert2MethodRef")
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Old-style way:
        List<Person> beatles = new ArrayList<>(); // Shared mutable state
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // Functional approach:
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        people = names.stream()
                .map(Person::new)    // uses the Person(String) ctr --> Stream<Person>
                // .map(Person::new) // uses the Person(Person) ctr
                .collect(Collectors.toList());
        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);  // dimension an array of Person whose size
                // is the same as the number of elements in the stream
                //.toArray(value -> new Person[value]);  // lambda equivalent of the constructor reference
        System.out.println(Arrays.toString(peopleArray));

        List<String> fullNames = Arrays.asList(
                "John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr");
        people = fullNames.stream()
                .map(name -> name.split(" "))
                .map(Person::new) // use the Person(String...) ctr
                .collect(Collectors.toList());
        System.out.println(people);
        System.out.println(people.getClass().getName());

        // p1..p5 | p6..p10 | p11..p15 | p16..p20  // say you have 4 cores and run in parallel
        //   l1       l2         l3         l4     // Java uses a ForkJoinPool by default
        //                 list
        LinkedList<Person> linkedPersons = names.stream()
                .map(Person::new)
                .collect(
                        () -> new LinkedList<>(),                // Supplier<LinkedList>
                        (list, person) -> list.add(person),      // BiConsumer<LinkedList, Person>
                        (list1, list2) -> list1.addAll(list2));  // BiConsumer<LinkedList, LinkedList>
        System.out.println(linkedPersons);

        linkedPersons = names.parallelStream()
                .map(Person::new)
                .collect(
                        LinkedList::new,      // Supplier<LinkedList>
                        LinkedList::add,      // BiConsumer<LinkedList, Person>
                        LinkedList::addAll);  // BiConsumer<LinkedList, LinkedList>
        System.out.println(linkedPersons);

        linkedPersons = names.parallelStream()
                .map(Person::new)
                .collect(Collectors.toCollection(LinkedList::new)); // collect preserves order
        System.out.println(linkedPersons);

        List<Person> persons = new ArrayList<>();
        names.parallelStream()
                .map(Person::new)
                .forEach(persons::add);  // forEach does not preserve order
        System.out.println(persons);
    }
}
