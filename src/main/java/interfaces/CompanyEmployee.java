package interfaces;

import java.util.Objects;

// Records:
// - are immutable
// - are final
// - have accessors that use the names of the fields (e.g., first() and last())
// - autogenerate equals(), hashCode(), and toString()
// - do not have a default constructor
//public record CompanyEmployee(String first, String last) implements Company, Employee {
//
//    public String getName() {
//        return Employee.super.getName() + " works for " +
//                Company.super.getName();
//    }
//
//    @Override
//    public void doWork() {
//        System.out.println("Converting caffeine into code for $$$");
//    }
//}
public final class CompanyEmployee implements Company, Employee {
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
    public void doWork() {
        System.out.println("Converting caffeine into code for $$$");
    }

    @Override
    public String first() {
        return first;
    }

    @Override
    public String last() {
        return last;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CompanyEmployee) obj;
        return Objects.equals(this.first, that.first) &&
                Objects.equals(this.last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }

    @Override
    public String toString() {
        return "CompanyEmployee[" +
                "first=" + first + ", " +
                "last=" + last + ']';
    }

}
