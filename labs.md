# Labs for Functional Programming in Java

This document contains the lab exercises for the Functional Programming in Java course. Each exercise has TODO comments where you should implement the solution. Complete solutions can be found in the `solutions` branch.

## Table of Contents

- [Lambda Expressions and Functional Interfaces](#lambda-expressions-and-functional-interfaces)
  - [Exercise 1: Implement Consumer](#exercise-1-implement-consumer)
  - [Exercise 2: Implement Supplier](#exercise-2-implement-supplier)
  - [Exercise 3: Constructor References](#exercise-3-constructor-references)
  - [Exercise 4: Filter with Predicate](#exercise-4-filter-with-predicate)
  - [Exercise 5: Function Interface and Composition](#exercise-5-function-interface-and-composition)
  - [Exercise 6: Runnable and ExecutorService](#exercise-6-runnable-and-executorservice)
  - [Exercise 7: FileFilter Lambda Evolution](#exercise-7-filefilter-lambda-evolution)
  - [Exercise 8: Lazy Evaluation with Suppliers](#exercise-8-lazy-evaluation-with-suppliers)
- [Stream Operations](#stream-operations)
  - [Exercise 9: FlatMap with Nested Data Structures](#exercise-9-flatmap-with-nested-data-structures)
  - [Exercise 10: Sum Even and Odd Numbers](#exercise-10-sum-even-and-odd-numbers)
  - [Exercise 11: String Sorting](#exercise-11-string-sorting)
  - [Exercise 12: Book Sorting with Comparators](#exercise-12-book-sorting-with-comparators)
  - [Exercise 13: Collectors Demo](#exercise-13-collectors-demo)
  - [Exercise 14: Parallel Streams](#exercise-14-parallel-streams)
- [BigDecimal Stream Operations](#bigdecimal-stream-operations)
  - [Exercise 15: BigDecimal Reduce Operations](#exercise-15-bigdecimal-reduce-operations)
- ["Optional" Exercises](#optional-exercises)
  - [Exercise 16: Optional with DAO Pattern](#exercise-16-optional-with-dao-pattern)
  - [Exercise 17: Optional Chaining](#exercise-17-optional-chaining)
- [CompletableFuture Exercises](#completablefuture-exercises)
  - [Exercise 18: CompletableFuture Basics](#exercise-18-completablefuture-basics)
  - [Exercise 19: Await Quiescence](#exercise-19-await-quiescence)
- [Interface Evolution](#interface-evolution)
  - [Exercise 20: Multiple Interface Implementation](#exercise-20-multiple-interface-implementation)
- [Game of Thrones Exercises (Advanced)](#game-of-thrones-exercises-advanced)
- [Running the Tests](#running-the-tests)
- [Solutions](#solutions)
- [Tips](#tips)
- [Additional Resources](#additional-resources)

## Lambda Expressions and Functional Interfaces

### Exercise 1: Implement Consumer

Open the test file `src/test/java/lambdas/FunctionalInterfacesTest.java`

**Task:** Complete the following test methods by implementing `Consumer<String>`:

1. `implementConsumerUsingLambda` - Use a lambda expression
2. `implementConsumerUsingMethodReference` - Use a method reference

```java
@Test
public void implementConsumerUsingLambda() {
    // TODO: Implement Consumer<String> using a lambda expression
    // consumer.accept("Hello, World!");
}

@Test
public void implementConsumerUsingMethodReference() throws Exception {
    // TODO: Implement Consumer<String> using a method reference
    // consumer.accept("Hello, World!");
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 2: Implement Supplier

**Task:** Complete the three `Supplier` test methods:

1. `implementSupplierUsingAnonInnerClass` - Create a `Supplier<String>` that returns "Hello"
2. `implementSupplierUsingLambda` - Same using a lambda
3. `implementSupplierUsingMethodReference` - Create a `Supplier<Double>` using `Math::random`

```java
@Test
public void implementSupplierUsingAnonInnerClass() throws Exception {
    // TODO: Create a Supplier<String> that returns "Hello"
    // assertEquals("Hello", supplier.get());
}

@Test
public void implementSupplierUsingLambda() throws Exception {
    // TODO: Create a Supplier<String> using a lambda
    // assertEquals("Hello", supplier.get());
}

@Test
public void implementSupplierUsingMethodReference() throws Exception {
    // TODO: Create a Supplier<Double> that calls Math.random()
    // assertTrue(supplier.get() >= 0.0);
    // assertTrue(supplier.get() <= 1.0);
    
    // TODO: Create a DoubleSupplier that does the same
    // assertTrue(doubleSupplier.getAsDouble() >= 0.0);
    // assertTrue(doubleSupplier.getAsDouble() <= 1.0);
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 3: Constructor References

**Task:** Complete the `constructorReference` test method:

1. Add strings to a `HashSet` using constructor reference
2. Add strings to a `TreeSet` using constructor reference

```java
@Test
public void constructorReference() throws Exception {
    List<String> stringList = List.of("a", "b", "b", "c", "d", "d");
    // assertEquals(6, stringList.size());
    
    // TODO: Add the strings to a Set
    // assertEquals(4, strings.size());
    // assertEquals(HashSet.class, strings.getClass());
    
    // TODO: Add the strings to a TreeSet
    // assertEquals(4, sortedStrings.size());
    // assertEquals(TreeSet.class, sortedStrings.getClass());
    // assertEquals("a", sortedStrings.first());
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 4: Filter with Predicate

**Task:** Complete the predicate to accept only even numbers:

```java
@Test
public void filterWithPredicate() {
    // TODO: Fix the filter predicate to accept even numbers only
    // IntStream.of(3, 1, 4, 1, 5, 9)
    //         .filter(n -> true)  // accept even nums only
    //         .forEach(n -> assertTrue(n % 2 == 0));
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 5: Function Interface and Composition

Open the test file `src/test/java/lambdas/FunctionExercises.java`

**Task:** Complete various Function interface exercises:

1. Implement basic Function interface
2. Use method references with Functions
3. Compose functions using `andThen` and `compose`
4. Work with BiFunction, UnaryOperator, and BinaryOperator
5. Use Functions in stream operations

```java
@Test
public void implementFunction() {
    // TODO: Create a Function<String, Integer> that returns string length
    // Function<String, Integer> stringLength = ...
    
    // assertEquals(5, stringLength.apply("Hello"));
    // assertEquals(0, stringLength.apply(""));
}

@Test
public void functionComposition() {
    // TODO: Create and compose functions
    // Function<String, Integer> stringLength = ...
    // Function<Integer, String> toBinary = ...
    // Function<String, String> lengthToBinary = ...
    
    // assertEquals("101", lengthToBinary.apply("Hello")); // 5 in binary
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 6: Runnable and ExecutorService

Open the test file `src/test/java/lambdas/RunnableExercises.java`

**Task:** Evolve from anonymous inner classes to lambda expressions:

1. Implement Runnable as anonymous inner class
2. Convert to expression lambda
3. Use block lambda
4. Assign lambda to variable
5. Work with ExecutorService
6. Explore virtual threads (Java 21+)

```java
@Test
public void implementRunnableAsAnonymousInnerClass() throws InterruptedException {
    // TODO: Submit a Runnable using an anonymous inner class
    AtomicReference<String> result = new AtomicReference<>("");
    CountDownLatch latch = new CountDownLatch(1);
    
    // executorService.submit(new Runnable() { ... });
    
    // assertTrue(latch.await(1, TimeUnit.SECONDS));
    // assertEquals("Anonymous Inner Class", result.get());
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 7: FileFilter Lambda Evolution

Open the test file `src/test/java/lambdas/FileFilterExercises.java`

**Task:** Implement the `FileFilter` interface using different approaches:

1. Anonymous inner class implementation
2. Expression lambda
3. Block lambda with logging
4. Method reference  
5. Lambda assigned to variable
6. FilenameFilter vs FileFilter
7. Composed filters

```java
@Test
void listDirectories_anonInnerClass() {
    // TODO: Implement FileFilter using anonymous inner class
    // File[] dirs = root.listFiles(new FileFilter() {
    //     @Override
    //     public boolean accept(File pathname) {
    //         // Filter for directories
    //     }
    // });
}

@Test
void listDirectories_methodReference() {
    // TODO: Implement using method reference
    // File[] dirs = root.listFiles(...);
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 8: Lazy Evaluation with Suppliers

Open the test file `src/test/java/lambdas/LazyEvaluationExercises.java`

**Task:** Understand lazy evaluation using `Supplier<String>`:

1. Compare eager vs lazy evaluation in assertions
2. Demonstrate lazy logging with Java's Logger
3. Create custom lazy evaluation scenarios
4. Understand when to use Supplier for performance

```java
@Test
void eagerEvaluation() {
    boolean x = true;
    // TODO: Call assertTrue with getErrorMessage() directly
    // Notice that the message is generated even when the assertion passes
}

@Test
void lazyEvaluationWithLambda() {
    boolean x = true;
    // TODO: Call assertTrue with a lambda that calls getErrorMessage()
    // The message should NOT be generated when the assertion passes
}
```

[Back to Table of Contents](#table-of-contents)

## Stream Operations

### Exercise 7: FlatMap with Nested Data Structures

Open the test file `src/test/java/streams/FlatMapExercises.java`

**Task:** Work with nested data structures using map and flatMap:

1. Use `map` to transform customers to names
2. Observe nested structures with `map`
3. Use `flatMap` to flatten nested collections
4. Filter and transform with `flatMap`
5. Combine operations for complex transformations

```java
@Test
public void getAllOrdersFlat() {
    // TODO: Use flatMap to get all orders as a flat List<Order>
    // List<Order> allOrders = ...
    
    // assertEquals(5, allOrders.size());
}

@Test
public void combineCustomerNamesWithOrderIds() {
    // TODO: Create strings in format "CustomerName-OrderId" for all orders
    // List<String> customerOrderStrings = ...
    
    // assertTrue(customerOrderStrings.contains("Sheridan-1"));
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 9: Sum Even and Odd Numbers

Open the test file `src/test/java/streams/SumEvens.java`

**Task:** Complete the following methods using streams:

1. `addEvenElementsUsingStreams` - Sum all even numbers
2. `addOddElementsUsingStreams` - Sum all odd numbers

```java
@Test
public void addEvenElementsUsingStreams() {
    // TODO: Use streams to sum even elements
    // List<Integer> integers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5);
    // assertEquals(12, sum);
}

@Test
public void addOddElementsUsingStreams() {
    // TODO: Use streams to sum odd elements
    // List<Integer> integers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5);
    // assertEquals(24, sum);
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 10: String Sorting

Open the test file `src/test/java/streams/StringExercises.java`

**Task:** Complete various string sorting methods:

```java
@Test
public void stringLengthSort_lambda() {
    // TODO: Use lambda for the Comparator (reverse sort)
    
    // TODO: Use the "sorted" method on Stream
}

@Test
public void stringLengthSort_methodCall() {
    // TODO: Use a lambda that calls 'compareStrings' directly
}

@Test
public void stringLengthSort_methodRef() {
    // TODO: Use a method ref to 'compareStrings'
}

@Test
public void stringLengthSort_comparingInt() {
    // TODO: Use Comparator.comparingInt
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 12: Book Sorting with Comparators

Open the test file `src/test/java/sorting/BookSortingExercises.java`

**Task:** Practice sorting using modern Java Comparator methods:

1. Sort by natural ordering (publication year)
2. Sort by title alphabetically
3. Sort by author, then by title
4. Sort by year descending (newest first)
5. Sort by title length
6. Partition books by publication year
7. Group books by decade
8. Create complex multi-level sorts
9. Filter and sort books in a specific year range
10. Find books by a specific author

```java
@Test
public void sortByAuthorThenTitle() {
    // TODO: Sort by author, then by title for books by the same author
    // Hint: Use Comparator.comparing().thenComparing()
}

@Test
public void groupByDecade() {
    // TODO: Group books by the decade they were published
    // Hint: Use Collectors.groupingBy() with year/10*10
}

@Test
public void findKenKousenBooks() {
    // TODO: Find all books by Ken Kousen and sort by publication year
}
```

**Key Concepts:**
- `Comparator.comparing()`
- `thenComparing()` for secondary sorts
- `reversed()` for descending order
- `comparingInt()` for numeric values
- `Collectors.partitioningBy()` and `groupingBy()`

[Back to Table of Contents](#table-of-contents)

### Exercise 13: Collectors Demo

**Task:** Complete the `demoCollectors` test method:

```java
@Test
public void demoCollectors() {
    // TODO: Get only strings of even length
    // TODO: Add them to a LinkedList
    
    // TODO: Add the strings to a map of string to length
    
    // TODO: Filter out nulls, then print even-length strings
    
    // TODO: Function composition
    
    // TODO: Combine the two predicates and use the result to print non-null, even-length strings
}
```

[Back to Table of Contents](#table-of-contents)

### Exercise 14: Parallel Streams

Open the test file `src/test/java/streams/ParallelStreamExercises.java`

**Task:** Learn the basics of parallel stream processing:

1. Convert streams to parallel and observe thread behavior
2. Understand ordering differences between sequential and parallel
3. Use `forEachOrdered` to maintain order
4. Perform parallel reductions
5. Compare `findFirst` vs `findAny` behavior
6. Avoid stateful operations in parallel streams
7. Use thread-safe collectors
8. Understand when to use parallel streams

```java
@Test
public void basicParallelStream() {
    // TODO: Convert the stream to parallel and observe thread names
    // Stream.of("apple", "banana", "cherry", "date", "elderberry")
    //     .parallel()
    //     .forEach(fruit -> System.out.println(
    //         Thread.currentThread().getName() + ": " + fruit));
}

@Test
public void statefulOperationProblem() {
    // TODO: Learn why stateful operations are problematic in parallel
    // This is an anti-pattern example!
}

@Test
public void parallelPerformanceConsiderations() {
    // TODO: Understand when parallel streams are beneficial
    // Hint: Large datasets, CPU-intensive operations, independent work
}
```

**Key Concepts:**
- `parallel()` and `sequential()` methods
- Thread safety with parallel operations
- When to use parallel streams (performance considerations)
- Ordering guarantees with `forEachOrdered`
- Appropriate collectors for parallel operations

**Note:** For detailed performance analysis with benchmarks, see the separate JMH project repository.

[Back to Table of Contents](#table-of-contents)

## BigDecimal Stream Operations

### Exercise 15: BigDecimal Reduce Operations

Open the test file `src/test/java/streams/BigDecimalReduceExercises.java`

**Task:** Implement reduce operations with BigDecimal:

1. Sum first N BigDecimal values using `Stream.iterate`
2. Sum a list of prices with and without identity
3. Handle empty streams with reduce
4. Apply multiple discount factors
5. Understand non-associative operations
6. Create custom accumulators

```java
@Test
public void sumFirstNBigDecimals() {
    // TODO: Use Stream.iterate and reduce to sum 1+2+...+10
    // BigDecimal sum = Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
    //     .limit(10)
    //     .reduce(...)
}

@Test
public void sumWithEmptyStream() {
    // TODO: Compare reduce with and without identity on empty stream
    // What's the difference in return types?
}
```

**Alternative Demo:** Study `AlternativeReduceDemo.java` for examples using:
- String concatenation
- Custom object aggregation  
- Matrix operations
- Parallel reduce considerations

[Back to Table of Contents](#table-of-contents)

## "Optional" Exercises

Open the test files in `src/test/java/optional/`

### Exercise 16: Optional with DAO Pattern

**File:** `ProductDAOTest.java`

**Task:** Run the tests to understand:
- How `Optional` is used in DAO methods
- The difference between `findById` (returns `Optional`) and `getProductById`

[Back to Table of Contents](#table-of-contents)

### Exercise 17: Optional Chaining

**File:** `CompanyTest.java`

**Task:** Study the test to understand:
- How to chain `Optional` operations using `map` and `flatMap`
- When to use `map` vs `flatMap` with nested `Optional` values

[Back to Table of Contents](#table-of-contents)

## CompletableFuture Exercises

Open the test files in `src/test/java/concurrency/`

### Exercise 18: CompletableFuture Basics

**File:** `CompletableFutureTests.java`

**Task:** Run and study the tests to understand:
- Creating `CompletableFuture` instances
- Chaining operations (`thenApply`, `thenAccept`, `thenCompose`)
- Combining futures (`thenCombine`)
- Exception handling (`handle`, `exceptionally`)
- Using `allOf` and `anyOf`

[Back to Table of Contents](#table-of-contents)

### Exercise 19: Await Quiescence

**File:** `AwaitQuiesenceTest.java`

**Task:** Understand the differences between:
- `get()` - blocks and throws checked exceptions
- `join()` - blocks and throws unchecked exceptions
- `Thread.awaitQuiescence()` - waits for virtual threads (Java 21+)

[Back to Table of Contents](#table-of-contents)

## Interface Evolution

### Exercise 20: Multiple Interface Implementation

Open the test file `src/test/java/interfaces/CompanyEmployeeTest.java`

**Task:** Create a class that implements both `Company` and `Employee` interfaces, handling any default method conflicts.

```java
@Test
public void createClass() {
    // TODO: Create a class that implements both Company and Employee
    // TODO: Handle the conflict with the getSalary() default method
    // Company company = new MyCompany("MyCompany, Inc.");
    // assertEquals(0.0, company.getSalary());
}
```

[Back to Table of Contents](#table-of-contents)

## Game of Thrones Exercises (Advanced)

Open the test file `src/test/java/got/InMemoryMemberDAOTests.java`

**Note:** These tests are `@Disabled` by default. Remove the annotation to enable them.

**Tasks:** Implement the following stream operations:
- Finding by ID, name, and house
- Filtering and mapping
- Custom sorting
- Averaging and summing
- Grouping and partitioning
- Advanced collectors

[Back to Table of Contents](#table-of-contents)

## Running the Tests

From the command line:
```bash
# Run all tests
./gradlew test

# Run a specific test class
./gradlew test --tests "lambdas.FunctionalInterfacesTest"

# Run a specific test method
./gradlew test --tests "lambdas.FunctionalInterfacesTest.implementConsumerUsingLambda"
```

From your IDE:
- Use the built-in test runner
- Right-click on test classes or methods to run them

## Solutions

Complete solutions for all exercises can be found in the `solutions` branch:

```bash
git checkout solutions
```

Compare your implementations with the solutions to learn different approaches.

## Tips

1. Start with the simpler exercises (lambdas and functional interfaces) before moving to streams
2. Use your IDE's auto-completion to explore available methods
3. Refer to the Java API documentation for `Stream`, `Optional`, and `CompletableFuture`
4. The test names often hint at the expected implementation approach
5. If stuck, look at similar patterns in the demo classes under `src/main/java`

## Additional Resources

- `Java_Upgrade_Slides.pdf` - Course slides with theoretical background
- Demo classes in `src/main/java`:
  - `CollectorsDemo.java` - Advanced collector examples
  - `OptionalDemo.java` - Optional usage patterns
  - `CompletableFutureDemos.java` - Async programming examples
  - `UseProducts.java` - Practical stream operations