package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@SuppressWarnings("DuplicatedCode")
public class ProcessDictionary {
    private final Path dictionary = Paths.get("src/main/resources/web2");

    public void printTenLongestWords() {
        System.out.println("\nTen Longest Words:");
        try (Stream<String> words = Files.lines(dictionary)) {
            words.filter(s -> s.length() > 20)
                    .sorted(Comparator.comparingInt(String::length).reversed()
                            //.thenComparing(Comparator.reverseOrder()))
                    )
                    .limit(10)
                    .forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printWordsOfEachLength() {
        System.out.println("\nList of words of each length:");
        try (Stream<String> lines = Files.lines(dictionary)) {
            lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(String::length)) // Map<Integer,List<String>>
                    .forEach((len, wordList) -> System.out.println(len + ": " + wordList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printHowManyWordsOfEachLength() {
        System.out.println("\nNumber of words of each length:");
        try (Stream<String> lines = Files.lines(dictionary)) {
            lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting())) // Map<Integer,Long>
                    .forEach((len, num) -> System.out.printf("Length %d: Count %d%n", len, num));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printSortedMapOfWords() {
        System.out.println("\nNumber of words of each length (desc order):");
        try (Stream<String> lines = Files.lines(dictionary)) {
            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                    .collect(groupingBy(String::length, counting()));

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %d words%n", e.getKey(), e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printSortedMapOfWordsUsingBufferedReader() {
        System.out.println("\nNumber of words of each length (desc order):");
        try (Stream<String> lines =
                     new BufferedReader(new FileReader("src/main/resources/web2")).lines()) {
            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                    .collect(groupingBy(String::length, counting()));

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %d words%n", e.getKey(), e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProcessDictionary processDictionary = new ProcessDictionary();
        processDictionary.printTenLongestWords();
        processDictionary.printWordsOfEachLength();
        processDictionary.printHowManyWordsOfEachLength();
        processDictionary.printSortedMapOfWords();
        processDictionary.printSortedMapOfWordsUsingBufferedReader();
    }
}
