package sorting;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringSorterWithFilter {
    public static final Predicate<String> NON_NULLS = Objects::nonNull;
    public static final Predicate<String> EVENS = s -> s.length() % 2 == 0;

    public List<String> filterStrings(Predicate<String> predicate, List<String> strings) {
        return strings.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
