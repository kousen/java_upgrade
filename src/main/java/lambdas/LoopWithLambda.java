package lambdas;

import java.util.ArrayList;
import java.util.List;

public class LoopWithLambda {
    public static void main(String[] args) {
        String[] names = { "John", "Paul", "George", "Ringo" };
        List<Runnable> runnables = new ArrayList<>();
        for (String name : names) {
            runnables.add(() -> System.out.println(name));
        }
        runnables.forEach(Runnable::run);
    }
}
