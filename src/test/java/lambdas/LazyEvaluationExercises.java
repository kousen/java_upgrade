package lambdas;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyEvaluationExercises {

    private final Logger logger = Logger.getLogger(LazyEvaluationExercises.class.getName());

    private String getErrorMessage() {
        System.out.println("Generating error message...");
        return "x should be true";
    }

    private String getLogMessage() {
        System.out.println("Generating log message...");
        return "log message";
    }

    @Test
    void eagerEvaluation() {
        boolean x = true;
        
        // TODO: Call assertTrue with getErrorMessage() directly
        // Notice that the message is generated even when the assertion passes
        // assertTrue(x, getErrorMessage());
        
        // TODO: Call logger.fine with getLogMessage() directly
        // Notice that the message is generated even at fine log level
        // logger.fine(getLogMessage());
    }

    @Test
    void lazyEvaluationWithLambda() {
        boolean x = true;
        
        // TODO: Call assertTrue with a lambda that calls getErrorMessage()
        // The message should NOT be generated when the assertion passes
        // assertTrue(x, () -> ...);
        
        // TODO: Call logger.fine with a lambda that calls getLogMessage()
        // The message should NOT be generated when fine logging is disabled
        // logger.fine(() -> ...);
    }

    @Test
    void demonstrateLazyBenefit() {
        boolean condition = true;
        
        // TODO: Create an expensive operation that should only run when needed
        // Example: a method that concatenates many strings or does complex calculations
        
        // TODO: Show the difference between eager and lazy evaluation
        // 1. Call assertTrue with the expensive operation directly
        // 2. Call assertTrue with a lambda/supplier
        // 3. Observe the performance difference when condition is true
    }

    @Test
    void createCustomLazyLogger() {
        // TODO: Create a method that accepts a Supplier<String> for lazy logging
        // void logIfEnabled(Supplier<String> messageSupplier) { ... }
        
        // TODO: Test your custom lazy logger with both eager and lazy message generation
        // Compare the behavior when logging is enabled vs disabled
    }
}