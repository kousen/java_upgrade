package sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class SortingTest {

    // "Getter" methods on a record match
    // the property name, as in name(), priority()
    record Project(String name, int priority) {
    }

    private List<Project> projects;

    @BeforeEach
    void setUp() {
        // create a list of projects
        projects = List.of(
                new Project("Build a house", 1),
                new Project("Build a shed", 2),
                new Project("Build a garage", 3),
                new Project("Build a barn", 2),
                new Project("Build a fence", 1),
                new Project("Build a wall", 3)
        );
    }

    // sort by priority, then by name
    @Test
    public void sortByPriorityThenName() {
        projects.stream()
                .sorted(Comparator.comparingInt(Project::priority).reversed()
                        .thenComparing(Project::name))
                .forEach(System.out::println);
    }
}
