package interfaces;

// Records:
// - are immutable (no setters)
// - are final (no inheritance), also they extend java.lang.Record
// - define the primary constructor before the {}, also they don't have a default ctor
// - define accessors for each field of the form first(), last(), etc.
// - autogenerate toString(), equals(), and hashCode()
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
