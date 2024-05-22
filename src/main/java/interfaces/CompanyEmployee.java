package interfaces;

// Records:
// - introduced in Java 16
// - immutable data carriers
// - autogenerate equals, hashCode, and toString methods
// - primary (canonical) constructor defined before the body {}
// - "getter" methods match property names, as in first() and last()
// - final, and extend java.lang.Record
public record CompanyEmployee(String first, String last) implements Company, Employee {

    public String getName() {
        return "%s works for %s".formatted(Employee.super.getName(), Company.super.getName());
    }

    @Override
    public void doWork() {
        System.out.println("Converting caffeine into code for $$$");
    }
}
