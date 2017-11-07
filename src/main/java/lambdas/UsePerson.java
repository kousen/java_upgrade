package lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Java 7 (and below)
        List<Person> persons = new ArrayList<>();
        for (String name : names) {
            persons.add(new Person(name));
        }
        System.out.println(persons);

        // Use Person(String) ctor to a List
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);
        System.out.println(people.getClass().getName());

        // Use Person ctor ref to a Set
        Set<Person> peopleSet = names.stream()
                .map(Person::new)
                .collect(Collectors.toSet());
        System.out.println(peopleSet);
        System.out.println(peopleSet.getClass().getName());

        // Use Person ctor to a SortedSet
        SortedSet<Person> sortedSet = names.stream()
                .map(Person::new)
                // .collect(Collectors.toCollection(TreeSet::new)); // ctor ref
                .collect(Collectors.toCollection(  // use lambda to supply Comparator
                        () -> new TreeSet<>((p1, p2) -> p1.getName().compareTo(p2.getName()))));
        System.out.println(sortedSet);
        System.out.println(sortedSet.getClass().getName());
        System.out.println("First: " + sortedSet.first());
        System.out.println("Last : " + sortedSet.last());

        // Use Person ctor to a Person[]
        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
        System.out.println(Arrays.asList(peopleArray));
    }
}
