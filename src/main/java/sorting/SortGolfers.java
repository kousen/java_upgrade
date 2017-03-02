package sorting;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

public class SortGolfers {
    private List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);

    public List<Integer> getValuesBefore() {
        List<Integer> results = new ArrayList<>();
        for (Integer i : integers) {
            int val = i * 2;
            if (val % 3 == 0) {
                results.add(i);
            }
        }
        return results;
    }

    public List<Integer> getValuesAfter() {
        return integers.stream()
                .mapToInt(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .boxed()
                .collect(Collectors.toList());
    }

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
        Golfer nicklaus = golfers.get(0);
        System.out.println(nicklaus);

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

        List<Golfer> sorted = golfers.stream()
                .sorted(comparing(Golfer::getScore)
                        .thenComparing(Golfer::getLast)
                        .thenComparing(Golfer::getFirst))
                .collect(Collectors.toList());

        // The sort still maintains references to the same objects
        System.out.println(sorted.get(0) == nicklaus);

        // Copy, then sort the copies
        System.out.println("Sorting the copies:");
        sorted = golfers.stream()
                .map(Golfer::new)  // Make new golfers that are copies of the originals
                .sorted(comparing(Golfer::getScore)
                        .thenComparing(Golfer::getLast)
                        .thenComparing(Golfer::getFirst))
                .collect(Collectors.toList());
        System.out.println(sorted.get(0) == nicklaus);

        // Partition Golfers into two categories: above and below score = 70
        Map<Boolean, List<Golfer>> map = golfers.stream()
                .collect(Collectors.partitioningBy(g -> g.getScore() < 70));
        System.out.println(map);

        System.out.println("Printing the partitioned map:");
        map.forEach( (key, golferList) -> {
            System.out.println(key);
            golferList.forEach(System.out::println);
        });



    }
}
