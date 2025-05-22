package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Alternative demo for teaching reduce concepts if BigDecimal is already covered
 */
public class AlternativeReduceDemo {
    
    public static void main(String[] args) {
        System.out.println("=== String Concatenation Demo ===");
        stringConcatenationDemo();
        
        System.out.println("\n=== Custom Object Aggregation Demo ===");
        customObjectDemo();
        
        System.out.println("\n=== Matrix Operations Demo ===");
        matrixOperationsDemo();
    }
    
    // Demo 1: String concatenation (shows identity and associativity)
    static void stringConcatenationDemo() {
        List<String> words = List.of("Java", "Streams", "are", "powerful");
        
        // Without identity - returns Optional
        Optional<String> concatenated = words.stream()
                .reduce((a, b) -> a + " " + b);
        System.out.println("Without identity: " + concatenated.orElse(""));
        
        // With identity
        String sentence = words.stream()
                .reduce("", (a, b) -> a + " " + b);
        System.out.println("With identity: " + sentence);
        
        // Better approach with joining collector
        String properSentence = String.join(" ", words);
        System.out.println("Using join: " + properSentence);
    }
    
    // Demo 2: Aggregating custom objects
    static void customObjectDemo() {
        record Order(String product, int quantity, double price) {}
        
        List<Order> orders = List.of(
            new Order("Laptop", 2, 999.99),
            new Order("Mouse", 5, 29.99),
            new Order("Keyboard", 3, 79.99)
        );
        
        // Calculate total revenue
        double totalRevenue = orders.stream()
                .map(order -> order.quantity() * order.price())
                .reduce(0.0, Double::sum);
        System.out.println("Total revenue: $" + totalRevenue);
        
        // Find order with maximum value
        Optional<Order> maxOrder = orders.stream()
                .reduce((o1, o2) -> 
                    (o1.quantity() * o1.price() > o2.quantity() * o2.price()) ? o1 : o2);
        System.out.println("Highest value order: " + maxOrder.orElse(null));
    }
    
    // Demo 3: Matrix operations (advanced)
    static void matrixOperationsDemo() {
        // Representing a 2x2 matrix as a 4-element array
        List<double[]> matrices = List.of(
            new double[]{1, 2, 3, 4},      // Matrix A
            new double[]{5, 6, 7, 8},      // Matrix B
            new double[]{9, 10, 11, 12}    // Matrix C
        );
        
        // Add all matrices element-wise
        double[] sumMatrix = matrices.stream()
                .reduce(new double[]{0, 0, 0, 0}, 
                    (result, matrix) -> {
                        double[] sum = new double[4];
                        for (int i = 0; i < 4; i++) {
                            sum[i] = result[i] + matrix[i];
                        }
                        return sum;
                    });
        
        System.out.println("Sum of matrices: " + Arrays.toString(sumMatrix));
    }
    
    // Additional demo showing parallel reduce challenges
    static void parallelReduceDemo() {
        // This shows why the combining function matters in parallel streams
        List<String> letters = List.of("a", "b", "c", "d", "e");
        
        // Sequential - predictable order
        String sequential = letters.stream()
                .reduce("", (a, b) -> a + b);
        System.out.println("Sequential: " + sequential);
        
        // Parallel - order might vary
        String parallel = letters.parallelStream()
                .reduce("", 
                    (a, b) -> a + b,           // accumulator
                    (a, b) -> a + b);          // combiner
        System.out.println("Parallel: " + parallel);
    }
}