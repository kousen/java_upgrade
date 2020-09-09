package lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");
        // In Java 11: List.of("John", "Paul", "George", "Ringo")

        // Java 7:
        System.out.println("Java 7:");
        List<Person> beatles = new ArrayList<>();  // Shared mutable state
        for (String name : names) {                // Loops are sequential
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // Java 8+
        System.out.println("Java 8 stream with lambda");
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);

        // Parallel streams:
        System.out.println("Java 8 parallel stream");
        people = names.parallelStream()
                .map(name -> new Person(name))
                .collect(Collectors.toList());
        System.out.println(people);

        // Constructor references:
        System.out.println("Constructor refs with single name");
        people = names.stream()
                .map(Person::new)  // Which constructor? The one-arg ctor of type String
                .collect(Collectors.toList());
        System.out.println(people);

        List<String> famousPeople = Arrays.asList("Grace Hopper", "Ada Lovelace", "Barbara Liskov");
        System.out.println("Constructor refs with String array");
        people = famousPeople.stream()
                .map(s -> s.split("\\s+"))
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(people);

        System.out.println("Constructor refs into an array");
        Person[] peopleArray = names.stream()
                .map(Person::new)
                // .toArray(value -> new Person[value]);
                .toArray(Person[]::new);

        System.out.println(Arrays.toString(peopleArray));

        Optional<String> max = names.stream()
                .max((first, second) -> first.length() - second.length());
        System.out.println(max);

        // Add names to a map where the keys are the strings,
        //  and the values are the lengths of the strings
        Map<String, Integer> map = names.stream()
                .collect(Collectors.toMap(name -> name, String::length));
        System.out.println(map);
        map.forEach((key,value) -> System.out.println(key + ":" + value));
    }
}
