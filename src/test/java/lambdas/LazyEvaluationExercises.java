package lambdas;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;
import java.util.logging.Level;
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
        
        // Call assertTrue with getErrorMessage() directly
        // Notice that the message is generated even when the assertion passes
        assertTrue(x, getErrorMessage());
        
        // Call logger.fine with getLogMessage() directly
        // Notice that the message is generated even at fine log level
        logger.fine(getLogMessage());
    }

    @Test
    void lazyEvaluationWithLambda() {
        boolean x = true;
        
        // Call assertTrue with a lambda that calls getErrorMessage()
        // The message should NOT be generated when the assertion passes
        assertTrue(x, () -> getErrorMessage());
        
        // Call logger.fine with a lambda that calls getLogMessage()
        // The message should NOT be generated when fine logging is disabled
        logger.fine(() -> getLogMessage());
    }

    @Test
    void demonstrateLazyBenefit() {
        boolean condition = true;
        
        // Create an expensive operation that should only run when needed
        Supplier<String> expensiveOperation = () -> {
            System.out.println("Performing expensive operation...");
            // Simulate expensive string concatenation
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                result.append("Item ").append(i).append(", ");
            }
            return result.toString();
        };
        
        // Show the difference between eager and lazy evaluation
        // 1. Call assertTrue with the expensive operation directly (eager)
        System.out.println("Eager evaluation:");
        assertTrue(condition, expensiveOperation.get());
        
        // 2. Call assertTrue with a lambda/supplier (lazy)
        System.out.println("\nLazy evaluation:");
        assertTrue(condition, expensiveOperation);
        
        // When condition is true, lazy evaluation saves the expensive operation
    }

    @Test
    void createCustomLazyLogger() {
        // Create a method that accepts a Supplier<String> for lazy logging
        class CustomLogger {
            private final Level logLevel = Level.INFO;
            
            void logIfEnabled(Level level, Supplier<String> messageSupplier) {
                if (level.intValue() >= logLevel.intValue()) {
                    System.out.println(messageSupplier.get());
                }
            }
        }
        
        CustomLogger customLogger = new CustomLogger();
        
        // Test custom lazy logger with both eager and lazy message generation
        System.out.println("Testing custom logger:");
        
        // This will generate the message because INFO >= INFO
        customLogger.logIfEnabled(Level.INFO, () -> {
            System.out.println("Generating INFO message...");
            return "This is an INFO message";
        });
        
        // This will NOT generate the message because FINE < INFO
        customLogger.logIfEnabled(Level.FINE, () -> {
            System.out.println("Generating FINE message...");
            return "This is a FINE message";
        });
    }
}