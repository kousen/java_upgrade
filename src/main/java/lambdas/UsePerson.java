package lambdas;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr");

        // Classic, Java 7 approach:
        List<Person> beatles = new ArrayList<>(); // shared mutable state
        for (String name : names) {
            beatles.add(new Person(name));
        }
        System.out.println(beatles);

        // Using a (sequential) stream:
        Instant start = Instant.now();
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<MyPerson>
                .collect(Collectors.toList());  // Converts Stream<MyPerson> to List<MyPerson>
        System.out.println(people);
        System.out.println("Sequential: " + Duration.between(start, Instant.now()).toMillis() + "ms");

        // Method (constructor) reference:
        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(people);

        people = names.stream()
                .map(s -> s.split(" "))
                .map(strings -> new Person(strings[0], strings[1]))
                .collect(Collectors.toList());
        System.out.println(people);

        // In parallel (not worth it in this particular case):
        start = Instant.now();
        people = names.parallelStream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println("Parallel: " + Duration.between(start, Instant.now()).toMillisPart() + "ms");
        System.out.println(people);

        // Convert to an array instead of a collection:
        Person[] peopleArray = names.stream()
                .map(Person::new) // Constructor ref for Person
                .toArray(Person[]::new);  // Constructor ref for Person[]
        System.out.println(Arrays.toString(peopleArray));
    }
}
