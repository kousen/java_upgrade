package sorting;

import java.util.*;
import java.util.stream.Collectors;

public class SortGolfers {
    private final List<Golfer> golfers = Arrays.asList(
            new Golfer("Jack", "Nicklaus", 68),
            new Golfer("Tiger", "Woods", 70),
            new Golfer("Tom", "Watson", 70),
            new Golfer("Ty", "Webb", 68),
            new Golfer("Bubba", "Watson", 70)
    );

    // Java 7 and below sort
    public void java7Sort() {
        Collections.sort(golfers);  // natural order (by score)
        for (Golfer golfer : golfers) {
            System.out.println(golfer);
        }
    }

    // default sort is by score
    public void defaultSort() {
        golfers.stream()
                .sorted()  // Needs Golfer to implement Comparable
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
                        .thenComparing(Golfer::getFirst)
                )
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
        System.out.println("Java 7 sort");
        sg.java7Sort();
        System.out.println("\nDefault sort");
        sg.defaultSort();
        System.out.println("\nSort by score, then last");
        sg.sortByScoreThenLast();
        System.out.println("\nSort by score, then last, then first");
        sg.sortByScoreThenLastThenFirst();
        sg.partitionByScore();
    }
}
