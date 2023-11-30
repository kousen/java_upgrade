package interfaces;

public class CompanyEmployee implements Company, Employee {
    private final String firstName;
    private final String lastName;

    public CompanyEmployee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return Employee.super.getName() + " works for " +
               Company.super.getName();
    }

    @Override
    public String getFirst() {
        return firstName;
    }

    @Override
    public String getLast() {
        return lastName;
    }

    @Override
    public void doWork() {
        System.out.println("Converting caffeine into code for $$$");
    }
}
