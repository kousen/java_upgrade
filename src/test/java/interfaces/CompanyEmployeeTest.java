package interfaces;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Create a class called CompanyEmployee that implements both
//   the Company and Employee interfaces
// Implement the necessary methods
// Give the class a two-arg constructor that takes first and last name
// Implement the getName method so that the test below passes
public class CompanyEmployeeTest {

    @Test
    public void getName() {
        CompanyEmployee emp = new CompanyEmployee("Peter", "Gibbons");
        assertEquals("Peter Gibbons works for Initech", emp.getName());
    }

    @Test
    void forEachOnIterable() {
        List.of(new CompanyEmployee("Peter", "Gibbons"),
                new CompanyEmployee("Michael", "Bolton"),
                new CompanyEmployee("Bill", "Lumbergh"))
                .forEach(System.out::println);

        Map.ofEntries(Map.entry("a", 1),
                Map.entry("b", 2),
                Map.entry("c", 2))
                .forEach((k,v) -> System.out.println(k + " maps to " + v));
    }
}