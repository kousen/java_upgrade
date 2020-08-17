package interfaces;

public class Developer implements Employee {
    private final String first;
    private final String last;

    public Developer(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public String getName() {
        return "Employee: " + Employee.super.getName();
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
        System.out.println("Working...");
    }
}
