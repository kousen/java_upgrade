package sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class SortGolfers {
    private static List<Golfer> golfers = Arrays.asList(
            new Golfer("Jack", "Nicklaus", 68),
            new Golfer("Tiger", "Woods", 70),
            new Golfer("Tom", "Watson", 70),
            new Golfer("Ty", "Webb", 68),
            new Golfer("Bubba", "Watson", 70));

    public static void printSorted(Comparator<Golfer> comparator) {
        System.out.println("----------");
        golfers.stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        // sort by score
        SortGolfers.printSorted(comparingInt(Golfer::getScore));

        // sort by score, descending
        SortGolfers.printSorted(comparingInt(Golfer::getScore).reversed());

        // sort by score, then last name
        SortGolfers.printSorted(comparingInt(Golfer::getScore)
            .thenComparing(Golfer::getLast));

        // sort by score, then last name, then first name
        SortGolfers.printSorted(comparingInt(Golfer::getScore)
            .thenComparing(Golfer::getLast)
            .thenComparing(Golfer::getFirst));
    }
}
