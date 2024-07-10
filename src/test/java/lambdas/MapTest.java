package lambdas;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class MapTest {
    @Test
    void forEach_on_map() {
        // Given (The "of" method added to Map in Java 9; LVTI added in 10)
        var map = Map.of("a", 1, "b", 2, "c", 3);

        // When
        map.forEach((k, v) -> System.out.println(k + " -> " + v));

        // Then
        // No assertion, just check the console output
    }
}
