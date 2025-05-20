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
        // TODO: Create a Function<String, Integer> that returns string length
        // Function<String, Integer> stringLength = ...
        
        // assertEquals(5, stringLength.apply("Hello"));
        // assertEquals(0, stringLength.apply(""));
        // assertEquals(11, stringLength.apply("Lambda Test"));
    }

    @Test
    public void implementFunctionWithMethodReference() {
        // TODO: Create the same Function using a method reference
        // Function<String, Integer> stringLength = ...
        
        // assertEquals(5, stringLength.apply("Hello"));
        // assertEquals(0, stringLength.apply(""));
    }

    @Test
    public void functionComposition() {
        // TODO: Create two functions and compose them
        // 1. Function<String, Integer> to get string length
        // 2. Function<Integer, String> to convert to binary string
        // 3. Compose them to create Function<String, String>
        
        // Function<String, Integer> stringLength = ...
        // Function<Integer, String> toBinary = ...
        // Function<String, String> lengthToBinary = ...
        
        // assertEquals("101", lengthToBinary.apply("Hello")); // 5 in binary
        // assertEquals("1000", lengthToBinary.apply("JavaTest")); // 8 in binary
    }

    @Test
    public void andThenVsCompose() {
        // TODO: Demonstrate the difference between andThen and compose
        
        Function<Integer, Integer> multiplyBy2 = x -> x * 2;
        Function<Integer, Integer> add10 = x -> x + 10;
        
        // TODO: Create composed functions using both andThen and compose
        // Function<Integer, Integer> andThenResult = ...
        // Function<Integer, Integer> composeResult = ...
        
        // With x = 5:
        // andThen: (5 * 2) + 10 = 20
        // compose: (5 + 10) * 2 = 30
        
        // assertEquals(20, andThenResult.apply(5));
        // assertEquals(30, composeResult.apply(5));
    }

    @Test
    public void bifunctionExample() {
        // TODO: Create a BiFunction<String, String, Integer> that returns
        // the sum of the lengths of two strings
        
        // BiFunction<String, String, Integer> sumOfLengths = ...
        
        // assertEquals(9, sumOfLengths.apply("Hello", "Java"));
        // assertEquals(7, sumOfLengths.apply("Hi", "World"));
        // assertEquals(0, sumOfLengths.apply("", ""));
    }

    @Test
    public void unaryOperatorExample() {
        // TODO: Create a UnaryOperator<String> that converts to uppercase
        // Note: UnaryOperator<T> is Function<T, T>
        
        // UnaryOperator<String> toUpperCase = ...
        
        // assertEquals("HELLO", toUpperCase.apply("hello"));
        // assertEquals("JAVA", toUpperCase.apply("Java"));
    }

    @Test
    public void binaryOperatorExample() {
        // TODO: Create a BinaryOperator<Integer> that returns the maximum
        // Note: BinaryOperator<T> is BiFunction<T, T, T>
        
        // BinaryOperator<Integer> max = ...
        
        // assertEquals(10, max.apply(5, 10));
        // assertEquals(7, max.apply(7, 3));
        // assertEquals(0, max.apply(0, 0));
    }

    @Test
    public void functionInStreams() {
        List<String> words = Arrays.asList("lambda", "function", "java", "stream");
        
        // TODO: Use Function in map operations to:
        // 1. Get lengths of all words
        // 2. Convert all words to uppercase
        // 3. Get first character of each word
        
        // List<Integer> lengths = ...
        // List<String> upperCase = ...
        // List<Character> firstChars = ...
        
        // assertEquals(Arrays.asList(6, 8, 4, 6), lengths);
        // assertEquals(Arrays.asList("LAMBDA", "FUNCTION", "JAVA", "STREAM"), upperCase);
        // assertEquals(Arrays.asList('l', 'f', 'j', 's'), firstChars);
    }

    @Test
    public void customFunctionForComplexMapping() {
        List<String> sentences = Arrays.asList(
            "Hello World",
            "Java Programming",
            "Functional Interfaces"
        );
        
        // TODO: Create a Function that maps each sentence to a Map<String, Integer>
        // where keys are words and values are word lengths
        
        // Function<String, Map<String, Integer>> sentenceToWordLengths = ...
        
        // List<Map<String, Integer>> results = sentences.stream()
        //     .map(sentenceToWordLengths)
        //     .collect(Collectors.toList());
        
        // assertEquals(2, results.get(0).size());
        // assertEquals(5, results.get(0).get("Hello"));
        // assertEquals(5, results.get(0).get("World"));
    }

    @Test
    public void chainingMultipleFunctions() {
        // TODO: Create a chain of functions to process data:
        // 1. Parse string to integer
        // 2. Double the value
        // 3. Convert to hex string
        
        // Function<String, Integer> parseInt = ...
        // Function<Integer, Integer> doubleIt = ...
        // Function<Integer, String> toHex = ...
        // Function<String, String> parseDoubleToHex = ...
        
        // assertEquals("a", parseDoubleToHex.apply("5"));  // 5 * 2 = 10 = 0xa
        // assertEquals("20", parseDoubleToHex.apply("16")); // 16 * 2 = 32 = 0x20
    }
}