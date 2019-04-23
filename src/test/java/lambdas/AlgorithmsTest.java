package lambdas;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class AlgorithmsTest {
    @Test
    public void testFactorial() {
        assertEquals(BigInteger.ONE, Algorithms.factorial(0));
        assertEquals(BigInteger.ONE, Algorithms.factorial(1));
        assertEquals(BigInteger.valueOf(2), Algorithms.factorial(2));
        assertEquals(BigInteger.valueOf(6), Algorithms.factorial(3));
        assertEquals(BigInteger.valueOf(24), Algorithms.factorial(4));
        assertEquals(BigInteger.valueOf(120), Algorithms.factorial(5));
        System.out.println(Algorithms.factorial(50000).toString().length());
    }
}