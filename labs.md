# Labs for Functional Programming in Java

This document contains the lab exercises for the Functional Programming in Java course. Each exercise has TODO comments where you should implement the solution. Complete solutions can be found in the `solutions` branch.

## Lambda Expressions and Functional Interfaces

### Exercise 1: Implement Consumer

Open the test file `src/test/java/lambdas/FunctionalInterfacesTest.java`

**Task:** Complete the following test methods by implementing `Consumer<String>`:

1. `implementConsumerUsingLambda` - Use a lambda expression
2. `implementConsumerUsingMethodReference` - Use a method reference

```java
@Test
public void implementConsumerUsingLambda() throws Exception {
    // TODO: Implement Consumer<String> using a lambda expression
    // consumer.accept("Hello, World!");
}

@Test
public void implementConsumerUsingMethodReference() throws Exception {
    // TODO: Implement Consumer<String> using a method reference
    // consumer.accept("Hello, World!");
}
```

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

### Exercise 3: Constructor References

**Task:** Complete the `constructorReference` test method:

1. Add strings to a `HashSet` using constructor reference
2. Add strings to a `TreeSet` using constructor reference

```java
@Test
public void constructorReference() throws Exception {
    List<String> stringList = Arrays.asList("a", "b", "b", "c", "d", "d");
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

### Exercise 4: Filter with Predicate

**Task:** Complete the predicate to accept only even numbers:

```java
@Test
public void filterWithPredicate() throws Exception {
    // TODO: Fix the filter predicate to accept even numbers only
    // IntStream.of(3, 1, 4, 1, 5, 9)
    //         .filter(n -> true)  // accept even nums only
    //         .forEach(n -> assertTrue(n % 2 == 0));
}
```

## Stream Operations

### Exercise 5: Sum Even and Odd Numbers

Open the test file `src/test/java/streams/SumEvens.java`

**Task:** Complete the following methods using streams:

1. `addEvenElementsUsingStreams` - Sum all even numbers
2. `addOddElementsUsingStreams` - Sum all odd numbers

```java
@Test
public void addEvenElementsUsingStreams() {
    // TODO: Use streams to sum even elements
    // List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
    // assertEquals(12, sum);
}

@Test
public void addOddElementsUsingStreams() {
    // TODO: Use streams to sum odd elements
    // List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
    // assertEquals(24, sum);
}
```

### Exercise 6: String Sorting

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

### Exercise 7: Collectors Demo

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

## BigDecimal Stream Operations

Open the test file `src/test/java/streams/SumBigDecimalsTest.java`

**Task:** Study and run the existing tests to understand:
- How to sum `BigDecimal` values using streams
- The difference between using reduce with and without an identity value
- Why certain approaches are preferred for monetary calculations

## Optional Exercises

Open the test files in `src/test/java/optional/`

### Exercise 8: Optional with DAO Pattern

**File:** `ProductDAOTest.java`

**Task:** Run the tests to understand:
- How `Optional` is used in DAO methods
- The difference between `findById` (returns `Optional`) and `getProductById`

### Exercise 9: Optional Chaining

**File:** `CompanyTest.java`

**Task:** Study the test to understand:
- How to chain `Optional` operations using `map` and `flatMap`
- When to use `map` vs `flatMap` with nested `Optional` values

## CompletableFuture Exercises

Open the test files in `src/test/java/concurrency/`

### Exercise 10: CompletableFuture Basics

**File:** `CompletableFutureTests.java`

**Task:** Run and study the tests to understand:
- Creating `CompletableFuture` instances
- Chaining operations (`thenApply`, `thenAccept`, `thenCompose`)
- Combining futures (`thenCombine`)
- Exception handling (`handle`, `exceptionally`)
- Using `allOf` and `anyOf`

### Exercise 11: Await Quiescence

**File:** `AwaitQuiesenceTest.java`

**Task:** Understand the differences between:
- `get()` - blocks and throws checked exceptions
- `join()` - blocks and throws unchecked exceptions
- `Thread.awaitQuiescence()` - waits for virtual threads (Java 21+)

## Interface Evolution

### Exercise 12: Multiple Interface Implementation

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