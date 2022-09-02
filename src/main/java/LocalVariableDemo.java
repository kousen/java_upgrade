import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocalVariableDemo {
    public static void main(String[] args) {
        // Add up numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = 0;  // needs to be final or "effectively final"
        // Will not compile:
        // numbers.forEach(number -> sum += number);

        // Create a mutable object and modify it
        final List<Integer> result = new ArrayList<>(1);
        result.add(0);
        numbers.forEach(number -> {
            int total = result.get(0) + number;
            result.set(0, total);
        });
        System.out.println("The total is " + result.get(0));
    }
}
