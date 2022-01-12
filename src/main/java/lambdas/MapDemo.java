package lambdas;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("c", 2);
        map.put("a", 1);
        map.put("d", 3);
        map.put("b", 2);

        map.computeIfAbsent("e", String::length);
        map.computeIfAbsent("b", String::length);
        map.computeIfPresent("b", (k, v) -> {
            System.out.printf("k = %s,v = %d%n", k, v);
            return v * 2;
        });
        map.forEach((k,v) -> System.out.println(k + " maps to " + v));

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(System.out::println);

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }
}
