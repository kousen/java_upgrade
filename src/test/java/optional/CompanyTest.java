package optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class CompanyTest {
    private final Company company = new Company("MyBigCo");

    @Test
    void getManagerFromDepartment() {
        Optional<Department> department = company.getDepartment("IT");
        // Use map if Function returns a value
        Optional<Manager> manager = department.map(Department::getManager);
        System.out.println(manager);

        // Use flatMap if the Function returns an Optional<value>
        manager = department.flatMap(Department::getManagerOptional);
        System.out.println(manager);
    }
}
