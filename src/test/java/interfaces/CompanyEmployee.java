package interfaces;

public class CompanyEmployee implements Company, Employee {
    private final String first;
    private final String last;

    public CompanyEmployee(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public String getName() {
        return String.format("%s works for %s",
                             Employee.super.getName(),
                             Company.super.getName());
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
        System.out.println("Converting caffeine to code for money...");
    }
}
