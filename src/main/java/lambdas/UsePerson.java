package lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>


        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());

        System.out.println(people);
        System.out.println(people.getClass().getName());

        Set<Person> personSet = names.stream()
                .map(Person::new)
                .collect(Collectors.toSet());
        System.out.println(personSet);
        System.out.println(personSet.getClass().getName());

        LinkedList<Person> personList = names.stream()
                .peek(x -> System.out.println("Before map: " + x))
                .map(Person::new)
                .peek(x1 -> System.out.println("After map: " + x1))
                .collect(Collectors.toCollection(LinkedList::new));

        // 1..5 | 6..10 | 11..15 | 16..20
        //  R1     R2       R3       R4
        //              R
        people = names.stream()
                .map(Person::new)
                .collect(() -> new ArrayList<>(),               // Supplier
                        (objects, e) -> objects.add(e),         // Accumulate into list
                        (objects1, c) -> objects1.addAll(c));   // combine two collections

        people = names.parallelStream()
                .map(Person::new)
                .collect(ArrayList::new,      // Supplier
                        ArrayList::add,       // Accumulate into list
                        ArrayList::addAll);   // combine two collections

        // people elements in the same order as string names

        List<Person> others = new ArrayList<>();
        names.parallelStream()
                .map(Person::new)
                .forEach(p -> others.add(p));
        System.out.println("Not necesarily ordered: " + others);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
//        Person[] peopleArray = names.stream()
//                .map(Person::new)
//                .toArray(value -> new Person[value]);

        System.out.println(Arrays.toString(peopleArray));
    }
}
