package streams;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumBigDecimalsTest {
    private final SumBigDecimals summer = new SumBigDecimals();

    @Test
    void sumFirstN_asDoubles() {
        BigDecimal answer = summer.sumFirstN_asDoubles(10);
        assertEquals(BigDecimal.valueOf(55.0), answer);
    }

    @Test
    public void sumFirstN_usingReduce() {
        BigDecimal answer = summer.sumFirstN_usingReduce(10);
        assertEquals(BigDecimal.valueOf(55), answer);
    }

    @Test @Disabled("disable until demo")
    public void sumFirstNDoubledValues() {
        BigDecimal answer = summer.sumDoubles(10);

        // Used to show how reduce method without identity can give error
        assertEquals(BigDecimal.valueOf(110), answer);
    }

    @Test
    public void sumFirstNDoubledValuesInitialized() {
        BigDecimal answer = summer.sumDoublesInitialized(10);
        assertEquals(BigDecimal.valueOf(110), answer);
    }

}