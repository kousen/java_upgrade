package lambdas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertAllTest {

    // A record is an immutable data holder
    // - autogenerates equals(), hashCode(), and toString()
    // - primary or "canonical" constructor goes before the braces {}
    // - "getter" methods match the names of the record components, e.g., title(), author(), year()
    record Book(String title, String author, int year) {}

    private Book findByIsbn(String isbn) {
        if (!isbn.equals("978-0134685991")) {
            throw new IllegalArgumentException("Unknown ISBN: " + isbn);
        }
        return new Book("Effective Java", "Joshua Bloch", 2008);
    }

    @Test
    public void testBook() {
        Book book = findByIsbn("978-0134685991");
        assertAll(
            () -> assertEquals("Effective Java", book.title()),
            () -> assertEquals("Joshua Bloch", book.author()),
            () -> assertEquals(2008, book.year())
        );
    }

    @Test
    void testInvalidIsbn() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> findByIsbn("123"));
        assertEquals("Unknown ISBN: 123", exception.getMessage());
    }
}
