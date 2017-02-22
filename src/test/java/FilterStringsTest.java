import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FilterStringsTest {
    private FilterStrings demo = new FilterStrings();

    @Test
    public void evenLengthsJava7() throws Exception {
        System.out.println(demo.evenLengthsJava7());
    }

    @Test
    public void evenLengthsJava8() throws Exception {
        System.out.println(demo.evenLengthsJava8());
    }

    @Test
    public void myFilter() throws Exception {
        List<String> results = demo.applyFilter(s -> s.length() % 2 == 0);
        System.out.println(results);
    }

    @Test
    public void evens() throws Exception {
        List<String> results = demo.applyFilter(FilterStrings.EVENS);
        System.out.println(results);
    }

}