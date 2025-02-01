package interfaces;

// Includes:
// - toString, equals, hashCode
// - "getter" methods: first() and last()
// Records are final and extend java.lang.Record
public record CompanyEmployee(String first, String last) implements Company, Employee {

    public String getName() {
        return Employee.super.getName() + " works for " +
                Company.super.getName();
    }

    @Override
    public void doWork() {
        System.out.println("Converting caffeine into code for $$$");
    }
}
