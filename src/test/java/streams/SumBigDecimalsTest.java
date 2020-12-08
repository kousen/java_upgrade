package streams;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumBigDecimalsTest {
    private final SumBigDecimals summer = new SumBigDecimals();

    @Test
    public void sumFirstN_usingReduce() {
        BigDecimal answer = summer.sumFirstN_usingReduce(10);
        assertEquals(new BigDecimal("55"), answer);
    }

    @Test
    public void sumFirstN_usingReduce_verbose() {
        BigDecimal answer = summer.sumFirstN_usingReduce_verbose(10);
        assertEquals(new BigDecimal("55"), answer);
    }

    @Test  @Disabled("disable until demo")
    public void sumFirstNDoubledValues() {
        BigDecimal answer = summer.sumDoubles(10);

        // Used to show how reduce method without identity can give error
        assertEquals(new BigDecimal("110"), answer);
    }

    @Test
    public void sumFirstNDoubledValuesInitialized() {
        BigDecimal answer = summer.sumDoublesInitialized(10);
        assertEquals(new BigDecimal("110"), answer);
    }

    @Test
    void testProductOfFirstN() {
        assertAll(
                () -> assertEquals(1.0, summer.productOfFirstNDoubles(1)),
                () -> assertEquals(2.0, summer.productOfFirstNDoubles(2)),
                () -> assertEquals(6.0, summer.productOfFirstNDoubles(3))
        );

    }
}