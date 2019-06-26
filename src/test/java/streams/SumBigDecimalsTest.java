package streams;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class SumBigDecimalsTest {
    private SumBigDecimals summer = new SumBigDecimals();

    @Test
    public void sumFirstN_usingReduce() {
        BigDecimal answer = summer.sumFirstN_usingReduce(10);
        assertEquals(new BigDecimal("55"), answer);
    }

    @Test  // Should fail (off by 1)
    public void sumFirstNDoubledValues() {
        BigDecimal answer = summer.sumDoubles(10);
        assertEquals(new BigDecimal("110"), answer);
    }

    @Test
    public void sumFirstNDoubledValuesInitialized() {
        BigDecimal answer = summer.sumDoublesInitialized(10);
        assertEquals(new BigDecimal("110"), answer);
    }

}