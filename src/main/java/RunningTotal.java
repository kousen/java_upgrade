import java.util.Arrays;
import java.util.List;

public class RunningTotal {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        int total = 0;
        int max = 15;
        for (String s : strings) {
            if (total > max) {
                System.out.println("Exceeded max");
                break;
            }
            total += s.length();
        }
        System.out.println("total = " + total);


    }
}
