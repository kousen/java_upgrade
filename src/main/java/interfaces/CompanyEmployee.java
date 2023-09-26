package interfaces;

// Records in Java:
// - immutable data holders
// - primary constructor outside the braces {}
// - automatically generate toString, equals, and hashCode
// - "getters" match the names of the attributes

public record CompanyEmployee(String first, String last) implements Company, Employee {

    @Override
    public String getName() {
        return Employee.super.getName() + " works for " +
               Company.super.getName();
    }

    @Override
    public void doWork() {
        System.out.println("Converting caffeine into code for $$...");
    }
}
