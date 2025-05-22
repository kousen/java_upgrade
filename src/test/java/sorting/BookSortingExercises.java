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
        books = List.of(
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
        // TODO: Sort books by their natural ordering (publication year)
        // Hint: Use stream().sorted()
        
        // List<Book> sortedBooks = books.stream()
        //     .sorted()
        //     .toList();
        
        // assertEquals(Year.of(1994), sortedBooks.get(0).publishedYear());
        // assertEquals(Year.of(2023), sortedBooks.get(sortedBooks.size() - 1).publishedYear());
    }
    
    @Test
    public void sortByTitle() {
        // TODO: Sort books alphabetically by title
        // Hint: Use Comparator.comparing()
        
        // List<Book> sortedByTitle = books.stream()
        //     .sorted(Comparator.comparing(...))
        //     .toList();
        
        // assertEquals("Design Patterns", sortedByTitle.get(0).title());
        // assertEquals("The Pragmatic Programmer", sortedByTitle.get(sortedByTitle.size() - 1).title());
    }
    
    @Test
    public void sortByAuthorThenTitle() {
        // TODO: Sort by author, then by title for books by the same author
        // Hint: Use Comparator.comparing().thenComparing()
        
        // List<Book> sortedByAuthorTitle = books.stream()
        //     .sorted(Comparator.comparing(Book::author)
        //             .thenComparing(...))
        //     .toList();
        
        // Test that books are sorted by author
        // assertEquals("Brian Goetz", sortedByAuthorTitle.get(1).author());
        // assertEquals("Martin Fowler", sortedByAuthorTitle.get(sortedByAuthorTitle.size() - 2).author());
    }
    
    @Test
    public void sortByYearDescending() {
        // TODO: Sort by publication year in descending order (newest first)
        // Hint: Use Comparator.reversed()
        
        // List<Book> newestFirst = books.stream()
        //     .sorted(...)
        //     .toList();
        
        // assertEquals(Year.of(2023), newestFirst.get(0).publishedYear());
        // assertEquals(Year.of(1994), newestFirst.get(newestFirst.size() - 1).publishedYear());
    }
    
    @Test
    public void sortByTitleLength() {
        // TODO: Sort by the length of the title (shortest first)
        // Hint: Use Comparator.comparingInt()
        
        // List<Book> byTitleLength = books.stream()
        //     .sorted(Comparator.comparingInt(...))
        //     .toList();
        
        // assertTrue(byTitleLength.get(0).title().length() <= byTitleLength.get(1).title().length());
    }
    
    @Test
    public void partitionByPublicationYear() {
        // TODO: Partition books into those published before 2010 and after
        // Hint: Use Collectors.partitioningBy()
        
        // Map<Boolean, List<Book>> partitioned = books.stream()
        //     .collect(Collectors.partitioningBy(
        //         book -> ...));
        
        // List<Book> older = partitioned.get(true);
        // List<Book> newer = partitioned.get(false);
        
        // assertTrue(older.stream().allMatch(book -> book.publishedYear().getValue() < 2010));
        // assertTrue(newer.stream().allMatch(book -> book.publishedYear().getValue() >= 2010));
    }
    
    @Test
    public void groupByDecade() {
        // TODO: Group books by the decade they were published
        // Hint: Use Collectors.groupingBy() with year/10*10
        
        // Map<Integer, List<Book>> byDecade = books.stream()
        //     .collect(Collectors.groupingBy(
        //         book -> ...));
        
        // assertEquals(2, byDecade.get(1990).size()); // 1990s
        // assertEquals(3, byDecade.get(2000).size()); // 2000s
        // assertEquals(3, byDecade.get(2010).size()); // 2010s
        // assertEquals(1, byDecade.get(2020).size()); // 2020s
    }
    
    @Test
    public void customComparatorChain() {
        // TODO: Create a complex sort: by publication year descending, 
        // then by author ascending, then by title ascending
        
        // List<Book> complexSort = books.stream()
        //     .sorted(Comparator.comparing(Book::publishedYear).reversed()
        //             .thenComparing(...)
        //             .thenComparing(...))
        //     .toList();
        
        // Verify the sort worked correctly
        // Book first = complexSort.get(0);
        // Book second = complexSort.get(1);
        // assertTrue(first.publishedYear().getValue() >= second.publishedYear().getValue());
    }
    
    @Test
    public void findBooksInYearRange() {
        // TODO: Find all books published between 2000 and 2010 (inclusive)
        // and sort them by title
        
        // List<Book> booksInRange = books.stream()
        //     .filter(book -> ...)
        //     .sorted(...)
        //     .toList();
        
        // assertEquals(3, booksInRange.size());
        // assertTrue(booksInRange.stream()
        //     .allMatch(book -> book.publishedYear().getValue() >= 2000 
        //         && book.publishedYear().getValue() <= 2010));
    }
    
    @Test
    public void findKenKousenBooks() {
        // TODO: Find all books by Ken Kousen and sort by publication year
        
        // List<Book> kenBooks = books.stream()
        //     .filter(book -> ...)
        //     .sorted(...)
        //     .toList();
        
        // assertEquals(2, kenBooks.size());
        // assertEquals("Modern Java Recipes", kenBooks.get(0).title());
        // assertEquals("Mockito Made Clear", kenBooks.get(1).title());
    }
}