package lambdas;

import java.util.Objects;

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
        if (!(o instanceof Person person)) return false;

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
