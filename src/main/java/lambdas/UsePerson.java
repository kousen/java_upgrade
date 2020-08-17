package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Java 7
        List<Person> peopleList = new ArrayList<>();
        for (String name : names) {
            peopleList.add(new Person(name));
        }
        System.out.println(peopleList);

        // Java 8 streaming style
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println("Using lambda and stream  : " + people);

        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());

        System.out.println("Using ctor ref and stream: " + people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);

        System.out.println("Using array and stream   : " + Arrays.toString(peopleArray));
    }
}
