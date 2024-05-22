package interfaces;

public interface Employee {
    String first();

    String last();

    void doWork();

    default String getName() {
        return "%s %s".formatted(first(), last());
    }
}
