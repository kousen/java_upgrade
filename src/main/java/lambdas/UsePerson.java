package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    private final List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");
    private final List<String> fullNames = Arrays.asList("John Lennon", "Paul McCartney",
            "George Harrison", "Ringo Starr");

    // Java 7 and earlier:
    public List<Person> convertNamesToPeopleJava7() {
        List<Person> beatles = new ArrayList<>(); // Shared mutable state
        for (String name : names) {
            beatles.add(new Person(name));
        }
        return beatles;
    }

    // Use Java streams:
    @SuppressWarnings("Convert2MethodRef")
    public List<Person> convertNamesToPeopleStreams() {
        return names.stream()                   // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
    }

    public List<Person> convertNamesUsingCtorRef() {
        return names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
    }

    public Person[] convertNamesToPersonArray() {
        return names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
    }

    public List<Person> convertFullNamesToPerson() {
        return fullNames.stream()                       // Stream<String>
                .map(name -> name.split(" "))     // Stream<String[]>
                .map(Person::new)       // calls the vararg constructor
                .collect(Collectors.toList());
    }

    // p1..p5 | p6..p10 | p11..p15 | p16..p20  // 20 persons, 4 cores, and run in parallel
    //   l1       l2         l3         l4
    //                 list
    @SuppressWarnings("Convert2MethodRef")
    public List<Person> threeArgCollect() {
        return names.parallelStream()
                .peek(name -> System.out.printf("%s: %s%n", Thread.currentThread().getName(), name))
                .map(Person::new)
                .collect(
                        () -> new LinkedList<>(),               // Supplier<ArrayList>
                        (list, person) -> list.add(person),     // BiConsumer<ArrayList, Person>
                        (list1, list2) -> list1.addAll(list2)); // BiConsumer<ArrayList, ArrayList>
    }

    public List<Person> threeArgCollectMethodRefs() {
        return names.stream()
                .peek(name -> System.out.printf("%s: %s%n", Thread.currentThread().getName(), name))
                .map(Person::new)
                .collect(
                        LinkedList::new,     // Supplier<ArrayList>
                        LinkedList::add,     // BiConsumer<ArrayList, Person>
                        LinkedList::addAll); // BiConsumer<ArrayList, ArrayList>
    }
}
