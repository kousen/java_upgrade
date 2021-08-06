package optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    @Test
    void GetNullManager() {
        // Look up department somewhere
        Optional<Department> optDept = Optional.of(new Department("IT"));
        System.out.println(optDept);
        System.out.println(optDept.map(Department::getManager));
        System.out.println(optDept.map(Department::getManager)
                .orElseGet(() -> new Manager("Default")));
    }

    @Test
    void GetNonNullManager() {
        Department bedrock = new Department("Construction");
        bedrock.setManager(new Manager("Mr Slate"));
        Optional<Department> optDept = Optional.of(bedrock);
        System.out.println(optDept);
        System.out.println(optDept.map(Department::getManager));
        System.out.println(optDept.map(Department::getManager)
                .orElseGet(() -> new Manager("Default")));
    }

    @Test
    void flatMapOptional() {
        Department bedrock = new Department("Construction");
        bedrock.setManager(new Manager("Mr Slate"));
        Optional<Department> optDept = Optional.of(bedrock);
        System.out.println(optDept);
        System.out.println(optDept.flatMap(Department::getManagerOptional));
        System.out.println(optDept.flatMap(Department::getManagerOptional)
                .orElse(new Manager("Default")));
    }
}