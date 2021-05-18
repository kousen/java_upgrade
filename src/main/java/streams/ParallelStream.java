package streams;

import java.util.stream.Stream;

public class ParallelStream {
    public static int add(int a, int b) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        System.out.println(Thread.currentThread().getName());
        return a + b;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Integer sum = Stream.of(3, 1, 4, 1, 5, 9)
                .parallel()
                .reduce(ParallelStream::add)
                .orElse(0);
        long end = System.currentTimeMillis();
        System.out.println("The sum is " + sum + " after " + (end - start) + "ms");
    }
}
