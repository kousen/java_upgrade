package interfaces;

public interface Company {
    default String getName() {
        return "Initech";
    }

    // String getName();  // Java 7 or earlier
}
