package optional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Company {
    private final String name;

    private final Map<String, Department> departmentMap = new HashMap<>();

    public Company(String name) {
        this.name = name;

        Department it = new Department("IT");
        Department sales = new Department("Sales");
        Department finance = new Department("Finance");
        Department accounting = new Department("Accounting");

        Manager mrSlate = new Manager("Mr Slate");
        Manager mrBurns = new Manager("Mr Burns");
        Manager janeway = new Manager("Admiral Janeway");

        sales.setManager(mrBurns);
        finance.setManager(mrSlate);
        accounting.setManager(janeway);

        Arrays.asList(it, sales, finance, accounting).forEach(
                dept -> departmentMap.put(dept.getName(), dept)
        );
    }

    public Optional<Department> getDepartment(String name) {
        return Optional.ofNullable(departmentMap.get(name));
    }

    public String getName() {
        return name;
    }
}
