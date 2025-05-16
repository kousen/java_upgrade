package streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StringExercises {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private List<String> strings = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        strings.add("this");
        strings.add("is");
        strings.add("a");
        strings.add("list");
        strings.add("of");
        strings.add("strings");
    }

    @Test
    public void stringLengthSort_InnerClass() {
        strings.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
    }

    @Test
    public void stringLengthSort_lambda() {
        strings.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void stringLengthSort_methodCall() {
        strings.sort((s1, s2) -> compareStrings(s1, s2));
    }

    @Test
    public void stringLengthSort_methodRef() {
        strings.sort(StringExercises::compareStrings);
    }

    @Test
    public void stringLengthSort_comparingInt() {
        strings.sort(Comparator.comparingInt(String::length));
    }


    private static int compareStrings(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length());
    }


}
