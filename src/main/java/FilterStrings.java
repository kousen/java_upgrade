import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterStrings {
    public static final Predicate<String> EVENS = s -> s.length() % 2 == 0;
    public static final Predicate<String> ODDS = s -> s.length() % 2 != 0;

    private List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public List<String> evenLengthsJava7() {
        List<String> results = new ArrayList<>();
        for (String s : strings) {
            if (s.length() % 2 == 0) {
                results.add(s);
            }
        }
        return results;
    }

    public List<String> applyFilter(Predicate<String> predicate) {
        return strings.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<String> evenLengthsJava8() {
        return strings.stream()
                .filter(s -> s.length() % 2 == 0)
//                .map(s -> {
//                    System.out.println(s);
//                    return s;
//                })
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}
