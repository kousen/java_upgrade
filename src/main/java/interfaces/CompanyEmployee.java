package interfaces;

// Records:
// - immutable data holders
// - final class and extend java.lang.Record, so can't be in an inheritance hierarchy
// - autogenerate toString(), equals(), and hashCode() methods
// - the "accessor" methods match the names of the properties (e.g. first() and last())
// - canonical constructor appears before the {}
public record CompanyEmployee(String first, String last) implements Company, Employee {

    public String getName() {
        return Employee.super.getName() + " works for " + Company.super.getName();
    }

    public String company() {
        return Company.super.getName();
    }

    public void doWork() {
        System.out.println("Work, work...");
    }

}
