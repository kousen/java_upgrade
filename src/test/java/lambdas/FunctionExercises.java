package lambdas;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionExercises {

    @Test
    public void implementFunction() {
        Function<String, Integer> stringLength = s -> s.length();
        
        assertEquals(5, stringLength.apply("Hello"));
        assertEquals(0, stringLength.apply(""));
        assertEquals(11, stringLength.apply("Lambda Test"));
    }

    @Test
    public void implementFunctionWithMethodReference() {
        Function<String, Integer> stringLength = String::length;
        
        assertEquals(5, stringLength.apply("Hello"));
        assertEquals(0, stringLength.apply(""));
    }

    @Test
    public void functionComposition() {
        Function<String, Integer> stringLength = String::length;
        Function<Integer, String> toBinary = Integer::toBinaryString;
        Function<String, String> lengthToBinary = stringLength.andThen(toBinary);
        
        assertEquals("101", lengthToBinary.apply("Hello")); // 5 in binary
        assertEquals("1000", lengthToBinary.apply("JavaTest")); // 8 in binary
    }

    @Test
    public void andThenVsCompose() {
        Function<Integer, Integer> multiplyBy2 = x -> x * 2;
        Function<Integer, Integer> add10 = x -> x + 10;
        
        Function<Integer, Integer> andThenResult = multiplyBy2.andThen(add10);
        Function<Integer, Integer> composeResult = multiplyBy2.compose(add10);
        
        // With x = 5:
        // andThen: (5 * 2) + 10 = 20
        // compose: (5 + 10) * 2 = 30
        
        assertEquals(20, andThenResult.apply(5));
        assertEquals(30, composeResult.apply(5));
    }

    @Test
    public void bifunctionExample() {
        BiFunction<String, String, Integer> sumOfLengths = 
            (s1, s2) -> s1.length() + s2.length();
        
        assertEquals(9, sumOfLengths.apply("Hello", "Java"));
        assertEquals(7, sumOfLengths.apply("Hi", "World"));
        assertEquals(0, sumOfLengths.apply("", ""));
    }

    @Test
    public void unaryOperatorExample() {
        UnaryOperator<String> toUpperCase = String::toUpperCase;
        
        assertEquals("HELLO", toUpperCase.apply("hello"));
        assertEquals("JAVA", toUpperCase.apply("Java"));
    }

    @Test
    public void binaryOperatorExample() {
        BinaryOperator<Integer> max = Integer::max;
        
        assertEquals(10, max.apply(5, 10));
        assertEquals(7, max.apply(7, 3));
        assertEquals(0, max.apply(0, 0));
    }

    @Test
    public void functionInStreams() {
        List<String> words = Arrays.asList("lambda", "function", "java", "stream");
        
        List<Integer> lengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        
        List<String> upperCase = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        
        List<Character> firstChars = words.stream()
                .map(word -> word.charAt(0))
                .collect(Collectors.toList());
        
        assertEquals(Arrays.asList(6, 8, 4, 6), lengths);
        assertEquals(Arrays.asList("LAMBDA", "FUNCTION", "JAVA", "STREAM"), upperCase);
        assertEquals(Arrays.asList('l', 'f', 'j', 's'), firstChars);
    }

    @Test
    public void customFunctionForComplexMapping() {
        List<String> sentences = Arrays.asList(
            "Hello World",
            "Java Programming",
            "Functional Interfaces"
        );
        
        Function<String, Map<String, Integer>> sentenceToWordLengths = sentence ->
            Arrays.stream(sentence.split(" "))
                  .collect(Collectors.toMap(
                      word -> word,
                      String::length
                  ));
        
        List<Map<String, Integer>> results = sentences.stream()
            .map(sentenceToWordLengths)
            .collect(Collectors.toList());
        
        assertEquals(2, results.get(0).size());
        assertEquals(5, results.get(0).get("Hello"));
        assertEquals(5, results.get(0).get("World"));
    }

    @Test
    public void chainingMultipleFunctions() {
        Function<String, Integer> parseInt = Integer::parseInt;
        Function<Integer, Integer> doubleIt = x -> x * 2;
        Function<Integer, String> toHex = Integer::toHexString;
        Function<String, String> parseDoubleToHex = parseInt
                .andThen(doubleIt)
                .andThen(toHex);
        
        assertEquals("a", parseDoubleToHex.apply("5"));  // 5 * 2 = 10 = 0xa
        assertEquals("20", parseDoubleToHex.apply("16")); // 16 * 2 = 32 = 0x20
    }
}