package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertLoops {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("one", "two", "three", "four", "five");
        List<String> evenLengths = new ArrayList<>();
        for (String s : strings) {
            if (s.length() % 2 == 0) {
                evenLengths.add(s.toUpperCase());
            }
        }
        System.out.println(evenLengths);

        List<String> evens = strings.stream()
                .filter(s -> s.length() % 2 == 0)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(evens);

        List<Person> people = Arrays.asList(new Person("Jean-Luc"),
                new Person("Will"),
                new Person("Geordi"),
                new Person("Wesley"));

        people.stream()
                .filter(person -> person.getName().length() % 2 == 0)  // Stream<Person>
                .map(Person::getName)              // Stream<String>
                .forEach(System.out::println);     // print each element
    }
}
