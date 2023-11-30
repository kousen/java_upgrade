package lambdas;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class IterateOverMap {
    @Test
    public void iterateOverMap() {
        var map = Map.of("a", 1, "b", 2, "c", 2);
        map.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}
