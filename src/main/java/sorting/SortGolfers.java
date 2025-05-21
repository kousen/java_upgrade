package sorting;

import java.util.*;
import java.util.stream.Collectors;

public class SortGolfers {
    private final List<Golfer> golfers = Arrays.asList(
            new Golfer("Jack", "Nicklaus", 68),
            new Golfer("Tiger", "Woods", 70),
            new Golfer("Tom", "Watson", 70),
            new Golfer("Ty", "Webb", 68),
            new Golfer("Bubba", "Watson", 70),
            new Golfer("Rose", "Zhang", 71)
    );

    // old-style sort
    public void oldSchoolSort() {
        Collections.sort(golfers);  // Sort by score only (inside Golfer class, compareTo method)
        golfers.forEach(System.out::println);
    }

    // default sort is by score, using streams
    public void defaultSort() {
        golfers.stream()
                .sorted()  // stateful intermediate operation
                .forEach(System.out::println); // terminal operation
    }

    // sort by score, then equal scores by last name
    public void sortByScoreThenLast() {
        golfers.stream()
                .sorted(Comparator.comparingInt(Golfer::score)
                        .thenComparing(Golfer::last))
                .forEach(System.out::println);
    }

    // sort by score, then by last, then by first
    public void sortByScoreThenLastThenFirst() {
        golfers.stream()
                .sorted(Comparator.comparingInt(Golfer::score)
                        .thenComparing(Golfer::last)
                        .thenComparing(Golfer::first))
                .forEach(System.out::println);
    }

    public void partitionByScore() {
        Map<Boolean, List<Golfer>> map = golfers.stream()
                .collect(Collectors.partitioningBy(
                        golfer -> golfer.score() < 70));

        map.forEach((k,v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });
    }

    public static void main(String[] args) {
        SortGolfers sg = new SortGolfers();
        System.out.println("--- oldSchoolSort ---");
        sg.oldSchoolSort();
        System.out.println("--- defaultSort ---");
        sg.defaultSort();
        System.out.println("--- sortByScoreThenLast ---");
        sg.sortByScoreThenLast();
        System.out.println("--- sortByScoreThenLastThenFirst ---");
        sg.sortByScoreThenLastThenFirst();
        System.out.println("--- partitionByScore ---");
        sg.partitionByScore();
    }
}
