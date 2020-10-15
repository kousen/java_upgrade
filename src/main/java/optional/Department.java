package optional;

import java.util.Optional;

public class Department {
    private final Integer id;
    private final String name;
    private Manager manager;
    private static int nextId;

    public Department(String name) {
        id = nextId++;
        this.name = name;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Optional<Manager> getManager() {
        return Optional.ofNullable(manager);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager=" + manager +
                '}';
    }
}
