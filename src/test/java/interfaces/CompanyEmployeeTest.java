package interfaces;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompanyEmployeeTest {
    private CompanyEmployee emp = new CompanyEmployee("Peter", "Gibbons");

    @Test
    public void getName() throws Exception {
        assertEquals("Peter Gibbons works for Initech", emp.getName());
    }

}