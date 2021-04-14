package lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Java 7:
        List<Person> beatles = new ArrayList<>();
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // Java 8+ streams
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        // Use Constructor ref instead of the lambda
        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(people);

        // 1..5|6..10|11..15|16..20   ForkJoinPool
        //  R1   R2     R3     R4
        //           R
        SortedSet<Person> personSet = names.parallelStream()
                .map(Person::new)
                .collect(() -> new TreeSet<>(),
                        (set, person) -> set.add(person),
                        (completeSet, set) -> completeSet.addAll(set));
        System.out.println(personSet);

        personSet = names.stream()
                .map(Person::new)
                .collect(TreeSet::new,     // make a new set
                        TreeSet::add,      // add each element to a set
                        TreeSet::addAll);  // combine the indivdual sets
        System.out.println(personSet);

        LinkedList<Person> personList = names.stream()
                .map(Person::new)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(personList);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);

        System.out.println(Arrays.toString(peopleArray));
    }
}
