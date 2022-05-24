package lambdas;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class MaxMinStringTest {
    @Test
    void getMaxStringByLength() {
        String[] strings = "this is a list of strings".split("\\s+");
        String max = Arrays.stream(strings)
                //.max((s1, s2) -> s1.length() - s2.length()).orElse("");
                .max(Comparator.comparingInt(String::length)).orElse("");
        System.out.println(max);
    }
}
