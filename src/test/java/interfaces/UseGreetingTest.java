package interfaces;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class UseGreetingTest {
    @Test
    public void isPrime() throws Exception {
        assertTrue(Stream.of(2, 3, 5, 7, 11, 13, 17, 19, 23)
                .allMatch(UseGreeting::isPrime));
        assertTrue(Stream.of(4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22)
                .noneMatch(UseGreeting::isPrime));

    }

}