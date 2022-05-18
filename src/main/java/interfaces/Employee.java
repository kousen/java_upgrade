package interfaces;

public interface Employee {
    String getFirst();

    String getLast();

    default void doWork() {
        System.out.println(getName() + " is converting caffeine into code for money...");
    }

    default String getName() {
        return String.format("%s %s", getFirst(), getLast());
    }
}
