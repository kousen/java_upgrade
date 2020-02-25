package streams;

import org.junit.Ignore;
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

    @Test
    public void sumFirstN_usingReduce_verbose() {
        BigDecimal answer = summer.sumFirstN_usingReduce_verbose(10);
        assertEquals(new BigDecimal("55"), answer);
    }

    @Test  @Ignore
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
    public void testProductOfDoubles() {
        assertEquals(120.0, summer.productOfDoubles(5), 0.001);
    }
}