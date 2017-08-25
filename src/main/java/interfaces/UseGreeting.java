package interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UseGreeting {
    public static boolean isPrime(int x) {
        int max = (int) (Math.sqrt(x) + 1);
        return x > 1 &&
                IntStream.range(2, max)
                        .noneMatch(val -> x % val == 0);
    }

    public static void main(String[] args) {
        Greeting greeting = s -> String.format("Hello, %s!", s);
        System.out.println(greeting.sayHello("Dolly"));

        greeting = new MyGreeting();
        System.out.println(greeting.sayHello(null));

        double x = 10;
        double y = 0.0;
        double z = x / y;
        System.out.println(z);

        // Want first hundred prime numbers

        Collection<Integer> primes = Collections.synchronizedCollection(new ArrayList<>());
        IntStream.iterate(2, i -> i + 1)
                .parallel()
                .filter(UseGreeting::isPrime)
                .limit(100)
                .forEach(primes::add);

        System.out.println(primes);

        primes = IntStream.iterate(2, i -> i + 1) // encounter order is numerical
                .parallel()
                .filter(UseGreeting::isPrime)
                .limit(100)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(primes);
        System.out.println(primes.size());

    }
}
