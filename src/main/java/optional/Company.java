package optional;

import java.util.*;

public class Company {
    private final Map<String, Department> departmentMap = new HashMap<>();
    private final String name;

    public Company(String name) {
        this.name = name;
        departmentMap.put("IT", new Department("IT"));
    }

    public Optional<Department> getDepartment(String name) {
        return Optional.ofNullable(departmentMap.get(name));
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
