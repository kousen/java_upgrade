package streams;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StringExercises {
    private final List<String> strings = Arrays.asList("this", "is", "a",
            "list", "of", "strings");

    @Test
    public void stringLengthSort_InnerClass() {     // Java 5, 6, 7
        strings.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        System.out.println(strings);
    }

    @Test
    public void stringLengthSort_lambda() {
        // Use lambda for the Comparator (reverse sort)

        // Use the "sorted" method on Stream
    }

    private static int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @Test  // Use a lambda that calls 'compareStrings' directly
    public void stringLengthSort_methodCall() {
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        // Add them to a LinkedList

        // Add the strings to a map of string to length

        // Filter out nulls, then print even-length strings

        // Combine the two predicates and use the result to print non-null, even-length strings
    }

}
