package streams;

import java.util.List;
import java.util.stream.Collectors;

public class FlattenList {
    private List<List<String>> listOfStrings;

    public FlattenList(List<List<String>> listOfStrings) {
        this.listOfStrings = listOfStrings;
    }

    public List<String> flatten() {
        return listOfStrings.stream()           // Stream<List<String>>
                .flatMap(strings -> strings.stream())       // Stream<String>
                .collect(Collectors.toList());  // List<String>
    }
}
