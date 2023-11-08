package lambdas;

import java.util.Map;

public class IterateOverMap {
    public static void main(String[] args) {
        Map<String, String> languageMap = Map.ofEntries(Map.entry("Java", "https://java.com"),
                Map.entry("Kotlin", "https://kotlinlang.org"),
                Map.entry("Scala", "https://scala-lang.org"),
                Map.entry("Groovy", "https://groovy-lang.org"));

        // Iterate over Map
        // We provide just the BiConsumer
        // The library applies it to each key/value pair
        languageMap.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
