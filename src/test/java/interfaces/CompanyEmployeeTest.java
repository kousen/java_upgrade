package interfaces;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

// Create a class called CompanyEmployee that implements both
//   the Company and Employee interfaces
// Implement the necessary methods
// Give the class a two-arg constructor that takes first and last name
// Implement the getName method so that the test below passes
public class CompanyEmployeeTest {
    private CompanyEmployee emp = new CompanyEmployee("Peter", "Gibbons");

    @Test
    public void getName() throws Exception {
        assertEquals("Peter Gibbons works for Initech", emp.getName());
    }
}