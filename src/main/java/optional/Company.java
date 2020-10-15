package optional;

import java.util.*;
import java.util.stream.Collectors;

public class Company {
    private final String name;
    private final Map<Integer, Department> departmentMap = new HashMap<>();

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Optional<Department> findById(Integer id) {
        return Optional.ofNullable(departmentMap.get(id));
    }

    public void addDepartment(Department department) {
        departmentMap.put(department.getId(), department);
    }

    public Collection<Department> getDepartments() {
        return departmentMap.values();
    }

    public List<Integer> getIds() {
        return new ArrayList<Integer>(departmentMap.keySet());
    }
}
