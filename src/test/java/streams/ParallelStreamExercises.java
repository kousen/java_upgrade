package streams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ParallelStreamExercises {

    @Test
    public void basicParallelStream() {
        // TODO: Convert the stream to parallel and observe thread names
        System.out.println("Number of processors: " + Runtime.getRuntime().availableProcessors());

        Stream.of("apple", "banana", "cherry", "date", "elderberry")
                .parallel()
                .forEach(fruit -> System.out.println(
                        Thread.currentThread().getName() + ": " + fruit));
    }

    @Test
    public void sequentialVsParallelOrder() {
        List<Integer> sequential = new ArrayList<>();
        List<Integer> parallel = new ArrayList<>();

        // TODO: Compare order of elements in sequential vs parallel streams

        IntStream.range(0, 10)
                .forEach(sequential::add);

        IntStream.range(0, 10)
                .parallel()
                .forEach(parallel::add);

        System.out.println("Sequential: " + sequential);
        System.out.println("Parallel: " + parallel);
        assertNotEquals(sequential, parallel); // Order likely different
    }

    @Test
    public void forEachOrderedMaintainsOrder() {
        List<Integer> parallelOrdered = new ArrayList<>();

        // TODO: Use forEachOrdered to maintain order with parallel stream

        IntStream.range(0, 10)
                .parallel()
                .forEachOrdered(parallelOrdered::add);

        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), parallelOrdered);
    }

    @Test
    void orderedWithCollect() {
        List<Integer> orderedInts = IntStream.range(0, 10)
                .parallel()
                .boxed()
                .toList();  // respects encounter order (as opposed to forEach)
        System.out.println("Ordered: " + orderedInts);
    }

    @Test
    public void parallelReduction() {
        // TODO: Sum numbers using parallel stream

        long start = System.nanoTime();
        int sum = IntStream.rangeClosed(1, 1000)
                .parallel()
                .sum();
        long end = System.nanoTime();

        assertEquals(500500, sum);
        System.out.println("Parallel sum took: " + (end - start) + "ns");
    }

    @Test
    public void findAnyVsFindFirst() {
        // TODO: Demonstrate difference between findFirst and findAny with parallel

        // Optional<Integer> first = IntStream.range(0, 1000)
        //     .parallel()
        //     .filter(i -> i > 950)
        //     .findFirst();

        // Optional<Integer> any = IntStream.range(0, 1000)
        //     .parallel()
        //     .filter(i -> i > 950)
        //     .findAny();

        // assertEquals(951, first.orElse(-1));
        // assertTrue(any.orElse(-1) > 950); // Could be any matching value
    }

    @Test
    public void statefulOperationProblem() {
        // This demonstrates why stateful operations can be problematic
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Bad practice - don't do this!
        List<Integer> doubledWrong = new ArrayList<>();

        // TODO: Show the problem with stateful operation in parallel
        // Note: This is an anti-pattern! 

        // numbers.parallelStream()
        //     .map(n -> n * 2)
        //     .forEach(doubledWrong::add); // Race condition!

        // Not guaranteed to have all elements or correct order
        // System.out.println("Wrong way: " + doubledWrong);

        // TODO: Show the correct way using collect

        // List<Integer> doubledRight = numbers.parallelStream()
        //     .map(n -> n * 2)
        //     .collect(Collectors.toList());

        // assertEquals(Arrays.asList(2, 4, 6, 8, 10), doubledRight);
    }

    @Test
    public void threadSafeCollector() {
        // TODO: Use a thread-safe collector for parallel operations

        // Map<String, Integer> wordLengths = Stream.of(
        //         "apple", "banana", "cherry", "date", "elderberry")
        //     .parallel()
        //     .collect(Collectors.toConcurrentMap(
        //         word -> word,
        //         String::length));

        // assertEquals(5, wordLengths.get("apple"));
        // assertEquals(6, wordLengths.get("banana"));
    }

    @Test
    public void customThreadSafeAccumulator() {
        // TODO: Count elements using thread-safe accumulator

        // AtomicInteger count = new AtomicInteger();

        // IntStream.range(0, 1000)
        //     .parallel()
        //     .forEach(i -> count.incrementAndGet());

        // assertEquals(1000, count.get());
    }

    @Test
    public void parallelStreamFromCollection() {
        List<String> fruits = Arrays.asList(
                "apple", "banana", "cherry", "date", "elderberry",
                "fig", "grape", "honeydew", "kiwi", "lemon"
        );

        // TODO: Check if stream is parallel by default
        // assertFalse(fruits.stream().isParallel());

        // TODO: Create parallel stream from collection
        // assertTrue(fruits.parallelStream().isParallel());

        // TODO: Convert sequential to parallel
        // assertTrue(fruits.stream().parallel().isParallel());

        // TODO: Convert parallel back to sequential
        // assertFalse(fruits.parallelStream().sequential().isParallel());
    }

    @Test
    public void parallelPerformanceConsiderations() {
        // Small datasets often perform worse with parallel streams
        // due to thread management overhead

        // TODO: Sum small dataset - parallel might be slower!
        List<Integer> smallData = IntStream.range(0, 100)
                .boxed()
                .toList();

        // int sum1 = smallData.stream()
        //     .mapToInt(Integer::intValue)
        //     .sum();

        // int sum2 = smallData.parallelStream()
        //     .mapToInt(Integer::intValue)
        //     .sum();

        // assertEquals(sum1, sum2);

        // Note: With small datasets, sequential is often faster!
        // Parallel streams shine with:
        // - Large datasets (thousands/millions of elements)
        // - CPU-intensive operations
        // - Independent operations (no shared state)
    }
}