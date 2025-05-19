# Project Context for Claude Code

## Overview
This is a Java training project focused on functional programming features in modern Java. The project contains:
- Lab exercises for students to complete
- Demo code showing Java features
- Complete solutions in the `solutions` branch

## Project Structure
- `src/main/java/` - Demo code and supporting classes
- `src/test/java/` - Exercise files with TODOs for students
- `labs.md` - Lab instructions with numbered exercises
- `solutions` branch - Contains completed exercises

## Key Java Features Covered
- Lambda expressions and method references
- Functional interfaces (Consumer, Supplier, Function, Predicate)
- Stream operations (map, flatMap, filter, collect)
- Optional handling
- CompletableFuture for async programming
- Interface default methods
- Modern Java features (records, pattern matching, virtual threads)

## Important Files
- `labs.md` - Main lab instructions, recently updated with table of contents
- Exercise files:
  - `src/test/java/lambdas/FunctionExercises.java`
  - `src/test/java/lambdas/RunnableExercises.java`
  - `src/test/java/streams/FlatMapExercises.java`
  - `src/test/java/streams/StringExercises.java`

## Development Guidelines
- Use modern Java idioms (e.g., `Stream.toList()` over `Collectors.toList()`)
- Follow existing code style and conventions
- Test exercises should have TODO comments for students
- Solutions branch should contain working implementations
- Run `./gradlew test` to verify solutions

## Git Workflow
- Main branch: Contains exercises with TODOs
- Solutions branch: Contains completed solutions
- Both branches should have identical `labs.md` structure
- Use conventional commit messages

## Testing
- Run all tests: `./gradlew test`
- Run specific test class: `./gradlew test --tests "package.ClassName"`
- Tests should be disabled by default in solutions branch if needed

## Recent Updates
- Added new exercises for Function interface, FlatMap, and Runnable evolution
- Updated labs.md with table of contents and navigation links
- Modernized code to use Java 16+ features where appropriate