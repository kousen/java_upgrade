package interfaces;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

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
    public void iterableDemo() {
        List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        integers.forEach(System.out::println);
    }
}