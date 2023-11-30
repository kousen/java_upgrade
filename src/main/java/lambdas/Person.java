package lambdas;

import java.util.Objects;

// Records:
// - are immutable data holders
// - have "canonical" or "primary" constructors BEFORE the braces
// - autogenerate equals(), hashCode(), and toString()
// - have "getters" whose names match the field names, e.g., name()
record PersonRecord(String name) {}

public class Person {
    private String name;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

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
