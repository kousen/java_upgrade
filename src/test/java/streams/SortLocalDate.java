package streams;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class SortLocalDate {
    private List<LocalDate> localDates = Arrays.asList(
            LocalDate.of(2017, Month.JANUARY, 12),
            LocalDate.of(2017, Month.DECEMBER, 1),
            LocalDate.of(2020, Month.SEPTEMBER, 2),
            LocalDate.of(2010, Month.JULY, 21),
            LocalDate.of(2017, Month.JANUARY, 15),
            LocalDate.of(2015, Month.JUNE, 4));

    @Test
    public void sortDatesNaturalSort() {
        Collections.sort(localDates);
        System.out.println(localDates);
    }

    @Test
    public void sortDateByMonth() {
        Collections.sort(localDates, new Comparator<LocalDate>() {
            @Override
            public int compare(LocalDate d1, LocalDate d2) {
                return d1.getMonthValue() - d2.getMonthValue();
            }
        });
        System.out.println(localDates);
    }

    @Test
    public void sortDateByMonthUsingLambda() {
        Collections.sort(localDates,
                (d1, d2) -> d1.getMonthValue() - d2.getMonthValue());
        System.out.println(localDates);
    }

    @Test
    public void sortDateByMonthUsingComparing() {
        Collections.sort(localDates, comparingInt(LocalDate::getMonthValue));
        System.out.println(localDates);
    }

    @Test
    public void sortDateUsingStream() {
        localDates.stream()
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void sortDateReverseOrderByMonthUsingStream() {
        localDates.stream()
                .sorted((d1, d2) -> d2.getMonthValue() - d1.getMonthValue())
                .forEach(System.out::println);
    }

    @Test
    public void sortDateReverseOrderByMonthUsingStreamComparing() {
        localDates.stream()
                .sorted(comparingInt(LocalDate::getMonthValue).reversed())
                .forEach(System.out::println);
    }

    @Test
    public void sortDateReverseOrderByYearMonthDay() {
        localDates.stream()
                .sorted(comparingInt(LocalDate::getYear)
                    .thenComparing(LocalDate::getMonthValue)
                    .thenComparing(LocalDate::getDayOfMonth)
                    .reversed())
                .forEach(System.out::println);
    }


}
