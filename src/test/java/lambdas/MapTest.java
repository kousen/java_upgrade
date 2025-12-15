package lambdas;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class MapTest {
    @Test
    void forEach_map() {
        var map = Map.of("one", 1, "two", 2, "three", 3);
        map.forEach((key, value) -> System.out.println(key + ": " + value));

        // iterate over keys
        map.keySet().forEach(key ->
                System.out.println("Key: " + key + ", value: " + map.get(key)));

        // iterate over entries (identical to a direct forEach)
        map.entrySet().forEach(entry ->
                System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
