package optional;

import java.util.Optional;

public class Department {
    private Manager manager;
    private final String name;

    public Department(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Optional<Manager> getManagerOptional() {
        return Optional.ofNullable(manager);
    }

    @Override
    public String toString() {
        return "Department{" +
                "manager=" + manager +
                ", name='" + name + '\'' +
                '}';
    }
}
