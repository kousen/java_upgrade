package streams;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BigDecimalReduceExercises {
    
    @Test
    public void sumFirstNBigDecimals() {
        // TODO: Sum the first 10 BigDecimal values (1, 2, 3, ... 10)
        // Use Stream.iterate to generate the values
        // Use reduce to sum them
        
        // BigDecimal sum = Stream.iterate(BigDecimal.ONE, bd -> bd.add(BigDecimal.ONE))
        //     .limit(10)
        //     .reduce(...)
        
        // assertEquals(new BigDecimal("55"), sum);
    }
    
    @Test
    public void sumListOfPrices() {
        List<BigDecimal> prices = List.of(
            new BigDecimal("19.99"),
            new BigDecimal("35.50"),
            new BigDecimal("12.75"),
            new BigDecimal("8.00")
        );
        
        // TODO: Sum all prices using reduce with identity
        // BigDecimal total = prices.stream()
        //     .reduce(...)
        
        // assertEquals(new BigDecimal("76.24"), total);
    }
    
    @Test
    public void sumWithEmptyStream() {
        List<BigDecimal> emptyPrices = List.of();
        
        // TODO: Sum an empty list - what happens without an identity?
        // Optional<BigDecimal> total = emptyPrices.stream()
        //     .reduce(BigDecimal::add);
        
        // assertTrue(total.isEmpty());
        
        // TODO: Now use reduce with identity - what's the result?
        // BigDecimal totalWithIdentity = emptyPrices.stream()
        //     .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // assertEquals(BigDecimal.ZERO, totalWithIdentity);
    }
    
    @Test
    public void multiplyDiscounts() {
        // Suppose we have a series of discount factors (0.9 = 10% off, 0.8 = 20% off)
        List<BigDecimal> discountFactors = List.of(
            new BigDecimal("0.9"),  // 10% off
            new BigDecimal("0.85"), // 15% off
            new BigDecimal("0.95")  // 5% off
        );
        
        // TODO: Calculate the final price after all discounts
        // Start with original price of 100
        BigDecimal originalPrice = new BigDecimal("100");
        
        // BigDecimal finalPrice = discountFactors.stream()
        //     .reduce(originalPrice, (price, discount) -> ...)
        
        // Calculate expected: 100 * 0.9 * 0.85 * 0.95 = 72.675
        // assertEquals(new BigDecimal("72.675"), finalPrice);
    }
    
    @Test
    public void demonstrateNonAssociativeOperation() {
        // This demonstrates why reduce needs an associative operation
        List<BigDecimal> values = List.of(
            new BigDecimal("10"),
            new BigDecimal("5"),
            new BigDecimal("2")
        );
        
        // TODO: Try subtraction (which is NOT associative)
        // What happens with: (10 - 5) - 2 vs 10 - (5 - 2)?
        
        // BigDecimal resultNoIdentity = values.stream()
        //     .reduce((a, b) -> a.subtract(b))
        //     .orElse(BigDecimal.ZERO);
        
        // assertEquals(new BigDecimal("3"), resultNoIdentity);
        
        // What about with identity?
        // BigDecimal resultWithIdentity = values.stream()
        //     .reduce(BigDecimal.ZERO, (a, b) -> a.subtract(b));
        
        // This gives a different result! Why?
        // assertEquals(new BigDecimal("-17"), resultWithIdentity);
    }
    
    @Test
    public void customAccumulator() {
        // Calculate both sum and count in one reduce operation
        List<BigDecimal> values = List.of(
            new BigDecimal("10.5"),
            new BigDecimal("20.3"),
            new BigDecimal("15.8"),
            new BigDecimal("5.5")
        );
        
        // TODO: Create a custom accumulator to track sum and count
        // Hint: You'll need a helper class or use an array
        
        class SumAndCount {
            BigDecimal sum;
            int count;
            
            SumAndCount(BigDecimal sum, int count) {
                this.sum = sum;
                this.count = count;
            }
        }
        
        // SumAndCount result = values.stream()
        //     .map(value -> new SumAndCount(value, 1))
        //     .reduce(new SumAndCount(BigDecimal.ZERO, 0),
        //         (acc, elem) -> new SumAndCount(
        //             acc.sum.add(elem.sum),
        //             acc.count + elem.count
        //         ));
        
        // Calculate average
        // BigDecimal average = result.sum.divide(
        //     BigDecimal.valueOf(result.count), 
        //     2, 
        //     BigDecimal.ROUND_HALF_UP
        // );
        
        // assertEquals(new BigDecimal("13.03"), average);
    }
}