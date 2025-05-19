# Project Context for Claude Code

## Overview
This is a comprehensive Java training project focused on functional programming features in modern Java (Java 8+). The project provides hands-on exercises for students to learn functional programming concepts through practical examples.

## Project Structure
```
java_upgrade/
├── src/main/java/    # Demo code and working examples
├── src/test/java/    # Exercise files with TODOs for students  
├── labs.md           # Comprehensive lab instructions
├── README.md         # Course overview and documentation
├── CLAUDE.md         # This file - project context for Claude Code
└── Java_Upgrade_Slides.pdf  # Course presentation slides
```

## Key Features
- 20 comprehensive exercises covering functional Java concepts
- Main branch contains exercises with TODO comments
- Solutions branch contains complete implementations
- Each exercise builds on previous concepts
- Modern Java idioms throughout (records, var, Stream.toList(), etc.)

## Recently Added Exercises
- FileFilter Lambda Evolution (Exercise 7)
- Lazy Evaluation with Suppliers (Exercise 8)
- Book Sorting with Comparators (Exercise 12)
- BigDecimal Reduce Operations (Exercise 14)
- Parallel Streams (Exercise 14)

## Java Features Covered
1. **Lambda Expressions & Functional Interfaces**
   - Consumer, Supplier, Function, Predicate
   - Method references and constructor references
   - Functional interface composition
   - Lazy evaluation patterns

2. **Stream API**
   - Basic operations (map, filter, reduce)
   - FlatMap for nested structures
   - Collectors and advanced aggregations
   - Parallel stream processing
   - Performance considerations

3. **Modern Java Features**
   - Optional patterns and chaining
   - CompletableFuture async programming
   - Records as data carriers
   - Interface default/static methods
   - Virtual threads (Java 21+)

## Important Files and Patterns

### Exercise Files to Remember
- `src/test/java/lambdas/FileFilterExercises.java` - Lambda evolution patterns
- `src/test/java/lambdas/LazyEvaluationExercises.java` - Supplier and lazy evaluation
- `src/test/java/sorting/BookSortingExercises.java` - Modern Comparator usage
- `src/test/java/streams/BigDecimalReduceExercises.java` - Reduce operations
- `src/test/java/streams/ParallelStreamExercises.java` - Parallel processing

### Demo Classes
- `src/main/java/sorting/SortGolfers.java` - Comparator demo (uses record)
- `src/main/java/streams/AlternativeReduceDemo.java` - Additional reduce examples

## Development Guidelines
1. **Code Style**
   - Use modern Java idioms (Stream.toList(), var where appropriate)
   - Prefer records over traditional POJOs for data carriers
   - Use method references where clearer than lambdas
   - Follow existing code formatting patterns

2. **Exercise Structure**
   - Each exercise should have clear TODO comments
   - Include sample expected results in comments
   - Provide hints for difficult concepts
   - Number exercises sequentially in labs.md

3. **Testing**
   - Exercises use JUnit 5
   - Tests should be self-contained
   - Include assertions to verify correct implementation
   - Use @Disabled annotation sparingly

## Git Workflow
- **main branch**: Exercises with TODOs for students
- **solutions branch**: Complete implementations
- **Training branches**: Month-year pattern (e.g., java_jan2025)
- Keep labs.md synchronized between branches
- Use descriptive commit messages

## Building and Testing
```bash
# Build project
./gradlew build

# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests "lambdas.FunctionalInterfacesTest"

# Run specific test method
./gradlew test --tests "lambdas.FunctionalInterfacesTest.implementConsumerUsingLambda"
```

## Recent Updates (January 2025)
1. Added FileFilter lambda evolution exercise
2. Added lazy evaluation with Suppliers exercise
3. Added BigDecimal reduce operations exercise
4. Added Book sorting with modern Comparators
5. Added basic parallel streams exercise
6. Converted Golfer class to record
7. Updated README.md with comprehensive documentation
8. Renumbered all exercises for clarity

## When Working on This Project
- Check `labs.md` for exercise organization
- Maintain consistency between main and solutions branches
- Update exercise numbers if adding new content
- Test both exercise and solution versions
- Consider how concepts build on each other
- Use descriptive variable names in examples

## Notes for Future Updates
- JMH benchmarking examples are in a separate repository
- Virtual threads features require Java 21+
- Some advanced Game of Thrones exercises are @Disabled by default
- Consider adding more real-world examples as new Java versions release