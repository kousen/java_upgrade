package interfaces;

// Records: (GA as of Java 16)
// - are immutable
// - have a "canonical" constructor before the {}
// - autogenerate equals, hashCode, toString
// - provide "getters" in the form of accessor methods like first() and last()
// - are final and have a superclass java.lang.Record
public record CompanyEmployee(String first, String last) implements Company, Employee {

    public CompanyEmployee {
        if (last == null) {
            throw new IllegalArgumentException("Last name cannot be null");
        }
    }

    // Implement the default method in the class --> class wins
    public String getName() {
        return Employee.super.getName() + " works for " + Company.super.getName();
    }

    @Override
    public void doWork() {
        System.out.println("Converting caffeine into code for $$$");
    }
}
