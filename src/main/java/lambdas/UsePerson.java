package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>


        people = names.stream()
                .map(Person::new)  // use the one-arg ctor Person(String)
                .collect(Collectors.toList());

        System.out.println(people);

        Person[] peopleArray = names.stream()
                // .map(Person::new)
                .map(name -> {
                    Person p = new Person();
                    p.setName(name);
                    return p;
                })
                .toArray(Person[]::new);

        System.out.println(Arrays.toString(peopleArray));



        // Let's look at ordering even in parallel
        // with 4 processors, each processor will get a name
        people = names.parallelStream()  // running in parallel
                .map(Person::new)  // use the one-arg ctor Person(String)
                .collect(Collectors.toList());
        System.out.println("From parallel: " + people);

        List<Person> beatles = Collections.synchronizedList(new ArrayList<>()); // Shared mutable state (dangerous!)
        names.parallelStream()
                .map(Person::new)
                .forEach(person -> beatles.add(person));
        System.out.println("From forEach: " + beatles);
    }
}
