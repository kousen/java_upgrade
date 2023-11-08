package lambdas;

import java.util.Objects;

public class Person {
    private String name;

    // Default constructor
    public Person() {}

    // Single-arg constructor
    public Person(String name) {
        this.name = name;
    }

    // Copy constructor
    public Person(Person other) {
        this.name = other.name;
    }

    // Varargs constructor
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
