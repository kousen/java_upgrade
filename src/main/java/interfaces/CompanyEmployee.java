package interfaces;

// Records:
// - Implemented in Java 16
// - Immutable
// - Autogenerate toString, equals, and hashCode
// - "Getters" use the property name as the method name, e.g., first(), last()
// - Primary (or canonical) constructor appears before the body
// - They are final and already extend java.lang.Record
public record CompanyEmployee(String first, String last) implements Company, Employee {

    @Override
    public String getName() {
        return Employee.super.getName() + " works for " +
               Company.super.getName();
    }

    @Override
    public void doWork() {
        System.out.println("Converting caffeine into code for $$$");
    }
}
