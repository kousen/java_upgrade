package interfaces;

// Records:
// - GA in Java 16
// - immutable data holders
// - autogenerate equals, hashCode, and toString methods
// - have "getters" of the form first(), last()
// - use a primary constructor before the class body
public class CompanyEmployee implements Company, Employee {
    private final String first;
    private final String last;

    public CompanyEmployee(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getName() {
        return Employee.super.getName() + " works for " +
               Company.super.getName();
    }

    @Override
    public String getFirst() {
        return first;
    }

    @Override
    public String getLast() {
        return last;
    }

    @Override
    public void doWork() {
        System.out.println("Converting caffeine into code for $$$...");
    }
}
