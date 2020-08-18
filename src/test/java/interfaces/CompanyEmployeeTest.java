package interfaces;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Create a class called CompanyEmployee that implements both
//   the Company and Employee interfaces
// Implement the necessary methods
// Give the class a two-arg constructor that takes first and last name
// Implement the getName method so that the test below passes
public class CompanyEmployeeTest {

    private String generateErrorMessage() {
        try {
            System.out.println("Inside generateErrorMessage");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "First name should be Peter";
    }

    @Test
    public void getName() {
        CompanyEmployee emp = new CompanyEmployee("Peter", "Gibbons");
        assertAll(
                () -> assertEquals("Peter", emp.getFirst(), () -> generateErrorMessage()),
                () -> assertEquals("Gibbons", emp.getLast()),
                () -> assertEquals("Peter Gibbons works for Initech", emp.getName())
        );
    }

    @Test
    void mapIteratorDemo() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1); map.put("b", 2); map.put("c", 2);

        map.forEach((k,v) -> System.out.println(k + " maps to " + v));
    }
}