package lambdas;

// Records:
// - are immutable data holders
// - automatically generate equals(), hashCode(), and toString()
// - "getters" match the property names, as in "name()"
// - primary constructor is declared before the braces {}
public record PersonRecord(String name) {

    public PersonRecord(String first, String last) {
        this(first + " " + last);
    }

    // Copy constructor
    public PersonRecord(PersonRecord other) {
        this(other.name);
    }

    public PersonRecord(String... names) {
        this(String.join(" ", names));
    }

}
