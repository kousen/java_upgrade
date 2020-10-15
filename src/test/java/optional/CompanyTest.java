package optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompanyTest {
    private final Company megaCorp = new Company("MegaCorp, Inc.");
    private Collection<String> departmentNames;

    @BeforeEach
    void setUp() {
        megaCorp.addDepartment(new Department("IT"));
        megaCorp.addDepartment(new Department("Finance"));
        megaCorp.addDepartment(new Department("Sales"));
        megaCorp.addDepartment(new Department("Accounting"));

        departmentNames = megaCorp.getDepartments()
                .stream()
                .map(Department::getName)
                .collect(Collectors.toList());
    }

    @Test
    void testGetDepartmentById() {
        megaCorp.getIds().stream()
                .map(megaCorp::findById)
                .forEach(optionalDept ->
                        assertAll(
                                () -> assertTrue(optionalDept.isPresent()),
                                () -> assertTrue(departmentNames.contains(
                                        optionalDept.get().getName()))
                        ));
    }

    @Test
    void testOptionalManagers() {
        Optional<Department> optionalDepartment =
                megaCorp.findById(megaCorp.getIds().get(0));

        // How do you get the Manager?
        // The ugly way:
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            Optional<Manager> optionalManager = department.getManager();
        }

        // the better way:
        Optional<Manager> optionalManager = optionalDepartment.flatMap(Department::getManager);

        optionalDepartment.ifPresent(System.out::println);
        optionalManager.ifPresent(System.out::println);
    }
}
