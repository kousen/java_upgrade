package lambdas;

import java.util.Objects;

// Records:
//  - became GA in Java 16
//  - immutable data holders
//  - constructor goes BEFORE the braces
//  - autogenerate toString(), equals(), hashCode() methods
//  - "getter" is a method that matches the name of the field, as in "name()"

// public record Person(String name) {
//     public Person() {
//         this("Unknown");
//     }
//
//     public Person(String... names) {
//         this(String.join(" ", names));
//     }
// }


// POJO: Plain Old Java Object
public class Person {
    private String name;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    // copy constructor
    public Person(Person other) {
        this.name = other.name;
    }

    public Person(String... names) {
        this.name = String.join(" ", names);
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

        return Objects.equals(name, person.name);
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
