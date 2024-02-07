package interfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void useAsRecord() {
        CompanyEmployee emp = new CompanyEmployee("Peter", "Gibbons");
        // All the executables are run, regardless of how many fail
        assertAll(
                () -> assertEquals("Peter Gibbons works for Initech", emp.getName()),
                () -> assertEquals("Peter", emp.first()),
                () -> assertEquals("Gibbons", emp.last())
        );

        // The first assertion that fails, aborts the rest of the test
        assertEquals("Peter Gibbons works for Initech", emp.getName());
        assertEquals("Peter", emp.first());
        assertEquals("Gibbons", emp.last());
        emp.doWork();
    }

    @Test
    void checkNullLastName() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new CompanyEmployee("Peter", null));
        assertEquals("Last name cannot be null", ex.getMessage());
    }
}