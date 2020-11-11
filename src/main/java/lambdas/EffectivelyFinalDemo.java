package lambdas;

import java.util.Arrays;
import java.util.List;

public class EffectivelyFinalDemo {
    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);

        // Java 7 style
        int total = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                total += num;
            }
        }
        System.out.println("The sum of the even numbers is " + total);

        // Attempt to use functional style
        total = 0;
//        nums.stream()
//                .filter(n -> n % 2 == 0)
//                .forEach(n -> total += n);  // Will not compile

        total = nums.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::valueOf)  // Stream<Integer> does not
                                             //  have a sum method, but IntStream does
                .sum();
        System.out.println("The sum of the even numbers is " + total);
    }
}
