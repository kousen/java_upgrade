package optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    private final Department department = new Department("IT");

    @Test
    void departmentWithoutManager() {
        Manager manager = department.getManager();
        assertNull(manager);

        Optional<Manager> managerOptional = department.getManagerOptional();
        assertFalse(managerOptional.isPresent());
    }
}