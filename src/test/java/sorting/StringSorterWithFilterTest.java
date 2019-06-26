package sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class StringSorterWithFilterTest {
    private StringSorterWithFilter sorter = new StringSorterWithFilter();

    @Test
    public void checkNonNullFilterWorks() {
        List<String> filtered = sorter.filterStrings(StringSorterWithFilter.NON_NULLS,
                Arrays.asList("here", null, "are", null, "some", "strings"));
        filtered.forEach(Assert::assertNotNull);
    }

    @Test
    public void checkEvensFilter() {
        List<String> filtered = sorter.filterStrings(StringSorterWithFilter.EVENS,
                Arrays.asList("this", "is", "a", "list", "of", "strings"));
        filtered.forEach(s -> assertTrue(s.length() % 2 == 0));
    }

    @Test
    public void combineFilters() {
        Predicate<String> both = StringSorterWithFilter.NON_NULLS.and(
                StringSorterWithFilter.EVENS);

        List<String> filtered = sorter.filterStrings(both,
                Arrays.asList("this", null, "is", "a", null, "list", "of", "strings"));
        filtered.forEach(Assert::assertNotNull);
        filtered.forEach(s -> assertEquals(0, s.length() % 2));
    }

    @Test
    public void provideYourOwnFilter() {
        List<String> filtered = sorter.filterStrings(s -> s.length() % 2 != 0,
                Arrays.asList("this", "is", "a", "list", "of", "strings"));

        filtered.forEach(s -> assertTrue(s.length() % 2 != 0));
    }
}