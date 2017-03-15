package interfaces;

public class CompanyEmployee implements Company, Employee {

    private String first = "Peter";
    private String last = "Gibbons";

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
        System.out.println("Looking like I'm working...");
    }

    @Override
    public String getName() {
        return String.format("%s works for %s",
                Employee.super.getName(), Company.super.getName());
    }
}
