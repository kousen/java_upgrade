package interfaces;

public interface Company {
    default String getName() {
        return "Initech";
    }

    // Java 7 or earlier:
    // String getName();
}
