package optional;

import java.util.Optional;

public class Department {
    private String name;
    private Manager manager;

    public Department(String name) {
        this.name = name;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    // If no manager, returns null
    public Manager getManager() {
        return manager;
    }

    public Optional<Manager> getManagerOptional() {
        return Optional.ofNullable(manager);
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}
