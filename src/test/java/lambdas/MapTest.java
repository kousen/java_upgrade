package lambdas;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class MapTest {

    @Test
    void iterateOverMap() {
        Map.of("a", 1, "b", 2, "c", 2)
                .forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}
