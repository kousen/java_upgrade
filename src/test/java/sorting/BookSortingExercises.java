package sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

record Book(String title, String author, Year publishedYear, String isbn) implements Comparable<Book> {
    
    @Override
    public int compareTo(Book other) {
        // Default comparison by publication year
        return this.publishedYear.compareTo(other.publishedYear);
    }
}

public class BookSortingExercises {
    
    private List<Book> books;
    
    @BeforeEach
    public void setUp() {
        books = Arrays.asList(
            new Book("The Pragmatic Programmer", "David Thomas", Year.of(1999), "978-0201616224"),
            new Book("Modern Java Recipes", "Ken Kousen", Year.of(2017), "978-1491973172"),
            new Book("Effective Java", "Joshua Bloch", Year.of(2018), "978-0134685991"),
            new Book("Java Concurrency in Practice", "Brian Goetz", Year.of(2006), "978-0321349606"),
            new Book("Design Patterns", "Erich Gamma", Year.of(1994), "978-0201633610"),
            new Book("Refactoring", "Martin Fowler", Year.of(2019), "978-0134757599"),
            new Book("Mockito Made Clear", "Ken Kousen", Year.of(2023), "978-1680509632"),
            new Book("Head First Design Patterns", "Eric Freeman", Year.of(2004), "978-0596007126")
        );
    }
    
    @Test
    public void sortByDefaultOrdering() {
        // Sort books by their natural ordering (publication year)
        List<Book> sortedBooks = books.stream()
            .sorted()
            .toList();
        
        assertEquals(Year.of(1994), sortedBooks.get(0).publishedYear());
        assertEquals(Year.of(2023), sortedBooks.get(sortedBooks.size() - 1).publishedYear());
    }
    
    @Test
    public void sortByTitle() {
        // Sort books alphabetically by title
        List<Book> sortedByTitle = books.stream()
            .sorted(Comparator.comparing(Book::title))
            .toList();
        
        assertEquals("Design Patterns", sortedByTitle.get(0).title());
        assertEquals("The Pragmatic Programmer", sortedByTitle.get(sortedByTitle.size() - 1).title());
    }
    
    @Test
    public void sortByAuthorThenTitle() {
        // Sort by author, then by title for books by the same author
        List<Book> sortedByAuthorTitle = books.stream()
            .sorted(Comparator.comparing(Book::author)
                    .thenComparing(Book::title))
            .toList();
        
        // Test that books are sorted by author
        assertEquals("Brian Goetz", sortedByAuthorTitle.get(1).author());
        assertEquals("Martin Fowler", sortedByAuthorTitle.get(sortedByAuthorTitle.size() - 2).author());
    }
    
    @Test
    public void sortByYearDescending() {
        // Sort by publication year in descending order (newest first)
        List<Book> newestFirst = books.stream()
            .sorted(Comparator.comparing(Book::publishedYear).reversed())
            .toList();
        
        assertEquals(Year.of(2023), newestFirst.get(0).publishedYear());
        assertEquals(Year.of(1994), newestFirst.get(newestFirst.size() - 1).publishedYear());
    }
    
    @Test
    public void sortByTitleLength() {
        // Sort by the length of the title (shortest first)
        List<Book> byTitleLength = books.stream()
            .sorted(Comparator.comparingInt(book -> book.title().length()))
            .toList();
        
        assertTrue(byTitleLength.get(0).title().length() <= byTitleLength.get(1).title().length());
    }
    
    @Test
    public void partitionByPublicationYear() {
        // Partition books into those published before 2010 and after
        Map<Boolean, List<Book>> partitioned = books.stream()
            .collect(Collectors.partitioningBy(
                book -> book.publishedYear().getValue() < 2010));
        
        List<Book> older = partitioned.get(true);
        List<Book> newer = partitioned.get(false);
        
        assertTrue(older.stream().allMatch(book -> book.publishedYear().getValue() < 2010));
        assertTrue(newer.stream().allMatch(book -> book.publishedYear().getValue() >= 2010));
    }
    
    @Test
    public void groupByDecade() {
        // Group books by the decade they were published
        Map<Integer, List<Book>> byDecade = books.stream()
            .collect(Collectors.groupingBy(
                book -> book.publishedYear().getValue() / 10 * 10));
        
        assertEquals(2, byDecade.get(1990).size()); // 1990s
        assertEquals(3, byDecade.get(2000).size()); // 2000s
        assertEquals(3, byDecade.get(2010).size()); // 2010s
        assertEquals(1, byDecade.get(2020).size()); // 2020s
    }
    
    @Test
    public void customComparatorChain() {
        // Create a complex sort: by publication year descending, 
        // then by author ascending, then by title ascending
        List<Book> complexSort = books.stream()
            .sorted(Comparator.comparing(Book::publishedYear).reversed()
                    .thenComparing(Book::author)
                    .thenComparing(Book::title))
            .toList();
        
        // Verify the sort worked correctly
        Book first = complexSort.get(0);
        Book second = complexSort.get(1);
        assertTrue(first.publishedYear().getValue() >= second.publishedYear().getValue());
    }
    
    @Test
    public void findBooksInYearRange() {
        // Find all books published between 2000 and 2010 (inclusive)
        // and sort them by title
        List<Book> booksInRange = books.stream()
            .filter(book -> book.publishedYear().getValue() >= 2000 
                && book.publishedYear().getValue() <= 2010)
            .sorted(Comparator.comparing(Book::title))
            .toList();
        
        assertEquals(3, booksInRange.size());
        assertTrue(booksInRange.stream()
            .allMatch(book -> book.publishedYear().getValue() >= 2000 
                && book.publishedYear().getValue() <= 2010));
    }
    
    @Test
    public void findKenKousenBooks() {
        // Find all books by Ken Kousen and sort by publication year
        List<Book> kenBooks = books.stream()
            .filter(book -> "Ken Kousen".equals(book.author()))
            .sorted(Comparator.comparing(Book::publishedYear))
            .toList();
        
        assertEquals(2, kenBooks.size());
        assertEquals("Modern Java Recipes", kenBooks.get(0).title());
        assertEquals("Mockito Made Clear", kenBooks.get(1).title());
    }
}