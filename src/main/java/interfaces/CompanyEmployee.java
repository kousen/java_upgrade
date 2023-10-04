package interfaces;

import java.util.Objects;

// Java records became GA in Java 16
// - immutable data holders
// - autogenerate equals, hashCode, toString
// - primary constructor that appears before the braces, as in
//   record CompanyEmployee(String first, String last) implements Company, Employee {}
// - can have other methods, but not modify the properties
// - property "getters" match the property names, as in first() and last()
public class CompanyEmployee implements Company, Employee {
    private final String first;
    private final String last;

    public CompanyEmployee(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getName() {
        return Employee.super.getName() + " works for " +
               Company.super.getName();
    }

    public void doWork() {
        System.out.println("Converting caffeine into code for $$$");
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
