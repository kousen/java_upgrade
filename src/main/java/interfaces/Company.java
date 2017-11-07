package interfaces;

public interface Company {
    default String getName() {
        return "Initech";
    }

    // Java 7: String getName();
}
