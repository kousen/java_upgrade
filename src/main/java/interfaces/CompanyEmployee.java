package interfaces;

// Records:
// - added in Java 16
// - immutable data carriers
// - automatically generate equals(), hashCode(), toString(), and "getters"
//   -- getters match the field names, as in first(), last()
// - primary ("canonical") constructor appears before the body

public record CompanyEmployee(String first, String last) implements Company, Employee {

    @Override
    public String getName() {
        return String.format("%s works for %s",
                Employee.super.getName(), Company.super.getName());
    }

    @Override
    public void doWork() {
        System.out.println("Converting caffeine into code for $$$");
    }
}
