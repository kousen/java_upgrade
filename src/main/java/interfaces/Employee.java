package interfaces;

public interface Employee {
    String first();

    String last();

    void doWork();

    default String getName() {
        return String.format("%s %s", first(), last());
    }
}
