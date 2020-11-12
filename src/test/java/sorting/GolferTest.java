package sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GolferTest {

    @Test
    void checkAllPropertiesOfAGolfer() {
        Golfer golfer = new Golfer("Tiger", "Wood", 68); // acquired from a service
        assertAll(
                () -> assertEquals("Tiger", golfer.getFirst()),
                () -> assertEquals("Wood", golfer.getLast()),
                () -> assertEquals(68, golfer.getScore())
        );
    }

    @Test
    void nullFirstNameCanThrowNPE() {
        Golfer golfer = new Golfer(null, "Wood", 68);
        assertThrows(NullPointerException.class, () -> golfer.getFirst().length());
    }
}