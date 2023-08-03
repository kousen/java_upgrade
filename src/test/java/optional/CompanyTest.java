package optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    private final Company company = new Company("MyCo");

    @Test
    void getDepartmentWithManager() {
        Optional<Department> dept = company.getDepartment("Accounting");
        assertTrue(dept.isPresent());
        Department department = dept.get();
        System.out.println(department.getName());
        System.out.println(department.getManager());
        System.out.println(department.getOptionalManager());
    }

    @Test
    void getDepartmentWithoutManager() {
        Optional<Department> dept = company.getDepartment("IT");
        assertTrue(dept.isPresent());
        Department department = dept.get();
        System.out.println(department.getName());
        System.out.println(department.getManager());
        System.out.println(department.getOptionalManager());
    }

    @Test
    void getOptionalDepartment() {
        Optional<Department> optionalDept = company.getDepartment("Whatever");
        assertTrue(optionalDept.isEmpty());
        System.out.println(optionalDept.map(Department::getManager));
        System.out.println(optionalDept.map(Department::getOptionalManager)); // dangerous
        System.out.println(optionalDept.flatMap(Department::getOptionalManager));
    }

    @Test
    void getOptionalDepartmentWithManager() {
        Optional<Department> optionalDept = company.getDepartment("Finance");
        assertTrue(optionalDept.isPresent());
        System.out.println(optionalDept.map(Department::getManager));
        System.out.println(optionalDept.map(Department::getOptionalManager)); // Optional<Optional<Manager>>
        System.out.println(optionalDept.flatMap(Department::getOptionalManager)); // Optional<Manager>
    }
}