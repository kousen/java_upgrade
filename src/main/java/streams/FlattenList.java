package streams;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FlattenList {
    private final List<List<String>> listOfStrings;

    public FlattenList(List<List<String>> listOfStrings) {
        this.listOfStrings = listOfStrings;
    }

    public List<String> flatten() {
        return listOfStrings.stream()           // Stream<List<String>>
                .flatMap(Collection::stream)    // Stream<String>
                .collect(Collectors.toList());  // List<String>
    }
}
