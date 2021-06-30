package sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortGolfers {
    private final List<Golfer> golfers = Arrays.asList(
            new Golfer("Jack", "Nicklaus", 68),
            new Golfer("Tiger", "Woods", 70),
            new Golfer("Tom", "Watson", 70),
            new Golfer("Ty", "Webb", 68),
            new Golfer("Bubba", "Watson", 70)
    );

    // default sort is by score
    public void defaultSort() {
        golfers.stream()
                .sorted()
                .forEach(System.out::println);
    }

    // sort by score, then equal scores by last name
    public void sortByScoreThenLast() {
        golfers.stream()
                .sorted(Comparator.comparingInt(Golfer::getScore)
                        .thenComparing(Golfer::getLast))
                .forEach(System.out::println);
    }

    // sort by score, then by last, then by first
    public void sortByScoreThenLastThenFirst() {
        golfers.stream()
                .sorted(Comparator.comparingInt(Golfer::getScore)
                        .thenComparing(Golfer::getLast)
                        .thenComparing(Golfer::getFirst))
                .forEach(System.out::println);
    }

    public void partitionByScore() {
        Map<Boolean, List<Golfer>> map = golfers.stream()
                .collect(Collectors.partitioningBy(
                        golfer -> golfer.getScore() < 70));

        map.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });
    }

    public static void main(String[] args) {
        SortGolfers sg = new SortGolfers();
//        sg.defaultSort();
//        sg.sortByScoreThenLast();
        sg.sortByScoreThenLastThenFirst();
//         sg.partitionByScore();
    }
}
