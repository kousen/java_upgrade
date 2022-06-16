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
        // department.getManager().getName() would throw an NPE
        System.out.println(department.getOptionalManager());

        // check if the optional is not empty, then print
        department.getOptionalManager().ifPresent(System.out::println);

        // if the optional is empty, supply a default
        System.out.println(department.getOptionalManager()
                .orElse(new Manager("Mr Bean"))
                .getName());
    }

    @Test
    void getOptionalDepartment() {
        Optional<Department> optionalDept = company.getDepartment("Whatever");
        System.out.println(optionalDept.map(Department::getManager));
        System.out.println(optionalDept.map(Department::getOptionalManager));
        System.out.println(optionalDept.flatMap(Department::getOptionalManager));
    }

    @Test
    void getOptionalDepartmentWithManager() {
        Optional<Department> optionalDept = company.getDepartment("Finance");
        System.out.println(optionalDept.map(Department::getManager));
        System.out.println(optionalDept.map(Department::getOptionalManager));
        System.out.println(optionalDept.flatMap(Department::getOptionalManager));
    }
}