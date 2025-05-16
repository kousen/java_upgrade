package interfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyEmployeeTest {
    private CompanyEmployee emp = new CompanyEmployee("Peter", "Gibbons");

    @Test
    public void getName() throws Exception {
        assertEquals("Peter Gibbons works for Initech", emp.getName());
    }

}