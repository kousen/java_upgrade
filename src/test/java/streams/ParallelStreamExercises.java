package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ParallelStreamExercises {
    
    @Test
    public void basicParallelStream() {
        // Convert the stream to parallel and observe thread names
        Stream.of("apple", "banana", "cherry", "date", "elderberry")
            .parallel()
            .forEach(fruit -> System.out.println(
                Thread.currentThread().getName() + ": " + fruit));
    }
    
    @Test
    public void sequentialVsParallelOrder() {
        List<Integer> sequential = new ArrayList<>();
        List<Integer> parallel = new ArrayList<>();
        
        // Compare order of elements in sequential vs parallel streams
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
        
        // Use forEachOrdered to maintain order with parallel stream
        IntStream.range(0, 10)
            .parallel()
            .forEachOrdered(parallelOrdered::add);
        
        assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), parallelOrdered);
    }
    
    @Test
    public void parallelReduction() {
        // Sum numbers using parallel stream
        int sum = IntStream.rangeClosed(1, 1000)
            .parallel()
            .sum();
        
        assertEquals(500500, sum);
    }
    
    @Test
    public void findAnyVsFindFirst() {
        // Demonstrate difference between findFirst and findAny with parallel
        Optional<Integer> first = IntStream.range(0, 1000)
            .parallel()
            .filter(i -> i > 950)
            .findFirst();
        
        Optional<Integer> any = IntStream.range(0, 1000)
            .parallel()
            .filter(i -> i > 950)
            .findAny();
        
        assertEquals(951, first.orElse(-1));
        assertTrue(any.orElse(-1) > 950); // Could be any matching value
    }
    
    @Test
    public void statefulOperationProblem() {
        // This demonstrates why stateful operations can be problematic
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // Bad practice - don't do this!
        List<Integer> doubledWrong = new ArrayList<>();
        
        // Show the problem with stateful operation in parallel
        // Note: This is an anti-pattern! 
        numbers.parallelStream()
            .map(n -> n * 2)
            .forEach(doubledWrong::add); // Race condition!
        
        // Not guaranteed to have all elements or correct order
        System.out.println("Wrong way: " + doubledWrong);
        
        // Show the correct way using collect
        List<Integer> doubledRight = numbers.parallelStream()
            .map(n -> n * 2)
            .collect(Collectors.toList());
        
        assertEquals(Arrays.asList(2, 4, 6, 8, 10), doubledRight);
    }
    
    @Test
    public void threadSafeCollector() {
        // Use a thread-safe collector for parallel operations
        Map<String, Integer> wordLengths = Stream.of(
                "apple", "banana", "cherry", "date", "elderberry")
            .parallel()
            .collect(Collectors.toConcurrentMap(
                word -> word,
                String::length));
        
        assertEquals(5, wordLengths.get("apple"));
        assertEquals(6, wordLengths.get("banana"));
    }
    
    @Test
    public void customThreadSafeAccumulator() {
        // Count elements using thread-safe accumulator
        AtomicInteger count = new AtomicInteger();
        
        IntStream.range(0, 1000)
            .parallel()
            .forEach(i -> count.incrementAndGet());
        
        assertEquals(1000, count.get());
    }
    
    @Test
    public void parallelStreamFromCollection() {
        List<String> fruits = Arrays.asList(
            "apple", "banana", "cherry", "date", "elderberry", 
            "fig", "grape", "honeydew", "kiwi", "lemon"
        );
        
        // Check if stream is parallel by default
        assertFalse(fruits.stream().isParallel());
        
        // Create parallel stream from collection
        assertTrue(fruits.parallelStream().isParallel());
        
        // Convert sequential to parallel
        assertTrue(fruits.stream().parallel().isParallel());
        
        // Convert parallel back to sequential
        assertFalse(fruits.parallelStream().sequential().isParallel());
    }
    
    @Test
    public void parallelPerformanceConsiderations() {
        // Small datasets often perform worse with parallel streams
        // due to thread management overhead
        
        // Sum small dataset - parallel might be slower!
        List<Integer> smallData = IntStream.range(0, 100)
            .boxed()
            .toList();
            
        int sum1 = smallData.stream()
            .mapToInt(Integer::intValue)
            .sum();
            
        int sum2 = smallData.parallelStream()
            .mapToInt(Integer::intValue)
            .sum();
            
        assertEquals(sum1, sum2);
        
        // Note: With small datasets, sequential is often faster!
        // Parallel streams shine with:
        // - Large datasets (thousands/millions of elements)
        // - CPU-intensive operations
        // - Independent operations (no shared state)
    }
}