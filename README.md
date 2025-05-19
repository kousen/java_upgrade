# Functional Programming in Java

A comprehensive training course covering modern Java features from Java 8 and beyond, with a focus on functional programming techniques.

**Instructor:** Ken Kousen  
**Created:** July 2016  
**Last Updated:** January 2025

## Course Overview

This course teaches developers how to use the functional programming features introduced in Java 8 and enhanced in subsequent versions. Students will learn to use lambda expressions, method references, streams, and other modern Java features to write more expressive and maintainable code.

## Prerequisites

- Solid understanding of Java fundamentals
- Experience with object-oriented programming
- Java 8 or later installed (Java 17+ recommended)

## Repository Structure

```
java_upgrade/
├── src/
│   ├── main/java/           # Demo code and examples
│   │   ├── concurrency/     # CompletableFuture demonstrations
│   │   ├── datetime/        # Date/time API examples
│   │   ├── got/            # Game of Thrones data model
│   │   ├── interfaces/      # Interface evolution examples
│   │   ├── io/             # I/O processing examples
│   │   ├── lambdas/        # Lambda expression demos
│   │   ├── lazy/           # Lazy evaluation patterns
│   │   ├── optional/       # Optional usage examples
│   │   ├── sorting/        # Comparator and sorting demos
│   │   └── streams/        # Stream operation examples
│   └── test/java/          # Hands-on exercises
│       ├── concurrency/    # CompletableFuture exercises
│       ├── got/           # Advanced stream exercises
│       ├── interfaces/     # Interface implementation
│       ├── lambdas/        # Functional interface exercises
│       ├── optional/       # Optional pattern exercises
│       ├── sorting/        # Comparator exercises
│       └── streams/        # Stream operation exercises
├── labs.md                 # Comprehensive exercise guide
├── CLAUDE.md              # Project context for Claude Code
└── Java_Upgrade_Slides.pdf # Course presentation slides
```

## Topics Covered

### 1. Lambda Expressions and Functional Interfaces
- Consumer, Supplier, Predicate, and Function interfaces
- Lambda expression syntax and evolution
- Method references and constructor references
- Functional interface composition
- Lazy evaluation patterns with Supplier

### 2. Stream API
- Creating and transforming streams
- Filtering, mapping, and reducing operations
- Collectors and advanced aggregations
- FlatMap for nested structures
- Parallel stream processing
- Performance considerations

### 3. Optional
- Optional creation and usage patterns
- Chaining operations with map and flatMap
- Best practices and anti-patterns
- Integration with DAO patterns

### 4. Modern Comparators
- Comparator.comparing() and thenComparing()
- Reverse ordering and custom comparisons
- Sorting with records
- Partitioning and grouping

### 5. Date/Time API
- LocalDate, LocalTime, and LocalDateTime
- Time zones and ZonedDateTime
- Duration and Period calculations
- Formatting and parsing

### 6. CompletableFuture
- Asynchronous programming patterns
- Chaining and combining futures
- Exception handling in async code
- Virtual threads (Java 21+)

### 7. Interface Evolution
- Default and static methods
- Multiple inheritance considerations
- Functional interfaces in the JDK

### 8. Advanced Topics
- Records as data carriers
- BigDecimal operations with streams
- Parallel processing best practices
- JMH benchmarking (separate repository)

## Exercises

The course includes 20 comprehensive exercises covering all major topics:

1. **Lambda Expressions** (Exercises 1-8)
   - Functional interface implementations
   - Method reference patterns
   - Lambda evolution examples
   - Lazy evaluation

2. **Stream Operations** (Exercises 9-15)
   - Basic stream operations
   - Advanced collectors
   - Parallel streams
   - BigDecimal reduce operations

3. **Advanced Features** (Exercises 16-20)
   - Optional patterns
   - CompletableFuture
   - Interface implementation
   - Complex stream operations

See [labs.md](labs.md) for detailed exercise instructions.

## Running the Exercises

### Using Gradle

```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests "lambdas.FunctionalInterfacesTest"

# Run specific test method
./gradlew test --tests "lambdas.FunctionalInterfacesTest.implementConsumerUsingLambda"
```

### Using Your IDE

Most modern IDEs (IntelliJ IDEA, Eclipse, VS Code) support running JUnit tests directly:
1. Open the test file
2. Click the run button next to the test method or class
3. View results in the test runner panel

## Branches

- `main` - Contains exercises with TODO comments for students
- `solutions` - Contains complete solutions for all exercises
- Training branches (e.g., `java_jan2025`) - Course-specific snapshots

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/kousen/java_upgrade.git
   cd java_upgrade
   ```

2. Ensure Java is installed:
   ```bash
   java -version  # Should show Java 8 or later
   ```

3. Build the project:
   ```bash
   ./gradlew build
   ```

4. Open [labs.md](labs.md) and start with Exercise 1

5. Run tests to verify your solutions:
   ```bash
   ./gradlew test
   ```

## Additional Resources

- **Course Slides**: `Java_Upgrade_Slides.pdf`
- **Demo Code**: Browse `src/main/java` for working examples
- **Solutions**: Check out the `solutions` branch to see completed exercises
- **Java Documentation**: [Official Java Documentation](https://docs.oracle.com/en/java/)

## Author

**Ken Kousen**  
- Email: ken.kousen@kousenit.com
- Bluesky: [@kenkousen.bsky.social](https://bsky.app/profile/kenkousen.bsky.social)
- LinkedIn: [Ken Kousen](https://www.linkedin.com/in/kenkousen/)
- Website: [www.kousenit.com](http://www.kousenit.com)

## Books by the Author

- *Mockito Made Clear* (Pragmatic Bookshelf, 2023)
- *Help Your Boss Help You* (Pragmatic Bookshelf, 2021)
- *Kotlin Cookbook* (O'Reilly, 2019)
- *Modern Java Recipes* (O'Reilly, 2017)
- *Gradle Recipes for Android* (O'Reilly, 2016)
- *Making Java Groovy* (Manning, 2013)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

Special thanks to all students and organizations who have taken this course and provided valuable feedback to improve the material.

---

*Note: This is an active training repository. Content is regularly updated to reflect the latest Java features and best practices.*