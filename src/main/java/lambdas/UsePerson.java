package lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsePerson {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        List<Person> people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());

        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);

        System.out.println(peopleArray);
    }
}
