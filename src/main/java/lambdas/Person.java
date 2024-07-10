package lambdas;

import java.util.Objects;

// Records:
// - introduced in Java 16
// - immutable data holders
// - automatically generate equals(), hashCode(), and toString() methods
// - primary ("canonical") constructor appears before the body
// - "getter" methods match the names of the components
// - are final and extend java.lang.Record
//
// public record PersonRecord(String name) {}
// "getter" method would be name()

public class Person {
    private String name;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    // "Copy" constructor
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
