package lambdas;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Person {
    private String name;

    public Person() {
        System.out.println("Default ctor on Person");
    }

    public Person(String name) {
        System.out.println("Person ctor on single string");
        this.name = name;
    }

    public Person(String... elements) {
        System.out.println("Person ctor on vararg list");
        name = String.join(" ", elements);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("Person(%s)", name);
    }
}
