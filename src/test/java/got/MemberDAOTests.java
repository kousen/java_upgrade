package got;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.*;

public class MemberDAOTests {
    private Collection<Member> allMembers = InMemoryMemberDAO.getInstance().getAll();

    /**
     * Find all members whose name starts with S and sort by id (natural sort)
     */
    @Test
    public void startWithS_sortByAlpha() {
        allMembers.stream()
                  .filter(member -> member.getName().startsWith("S"))
                  .sorted()
                  .forEach(System.out::println);
    }

    /**
     * Final all Starks and sort them by name
     */
    @Test
    public void starks_alphaByName() {
        allMembers.stream()
                  .filter(member -> member.getHouseName().equals("Stark"))
                  .sorted(comparing(Member::getName))
                  .forEach(System.out::println);
    }

    /**
     * Find all members whose salary is less than 80K and sort by house
     */
    @Test
    public void salaryLessThan_sortByHouseName() {
        allMembers.stream()
                  .filter(member -> member.getSalary() < 80000)
                  .sorted(comparing(Member::getHouseName))
                  .forEach(System.out::println);
    }

    /**
     * Sort members by house name, then by name
     */
    @Test
    public void sortByHouseName_sortByNameDesc() {
        allMembers.stream()
                  .sorted(comparing(Member::getHouseName)
                                  .thenComparing(Member::getName))
                  .forEach(System.out::println);
    }

    /**
     * Sort the Starks by birthdate
     */
    @Test
    public void starksByDob() {
        allMembers.stream()
                  .filter(member -> member.getHouseName().equals("Stark"))
                  .sorted(comparing(Member::getDob))
                  .forEach(System.out::println);

    }

    /**
     * Find all Kings and sort by name in descending order
     */
    @Test
    public void kingsByNameDesc() {
        allMembers.stream()
                  .filter(member -> member.getTitle().equals(Title.KING))
                  .sorted(comparing(Member::getName).reversed())
                  .forEach(System.out::println);
    }

    /**
     * Find the average salary
     */
    @Test
    public void averageSalary() {
        double ave = allMembers.stream()
                               .mapToDouble(Member::getSalary)
                               .average().orElse(0.0);
        System.out.println("The average salary is "
                                   + NumberFormat.getCurrencyInstance().format(ave));
    }

    /**
     * Get the names of all the Starks, sorted in natural order
     * (note _names_, not members)
     */
    @Test
    public void namesSorted() {
        allMembers.stream()
                  .map(Member::getName)
                  .sorted()
                  .forEach(System.out::println);
    }

    /**
     * Are all the salaries greater than 100K?
     */
    @Test
    public void salariesGT100k() {
        boolean gt100k = allMembers.stream()
                                   .allMatch(member -> member.getSalary() > 100000);
        System.out.println(gt100k ? "All salaries > 100K" : "There are some salaries < 100K");
    }

    /**
     * Are there any members of House Greyjoy?
     */
    @Test
    public void greyjoys() {
        boolean b = allMembers.stream()
                              .anyMatch(member -> member.getHouseName().equals("Greyjoy"));
        System.out.println(b);
    }

    /**
     * How many Lannisters are there?
     */
    @Test
    public void howManyLannisters() {
        long total = allMembers.stream()
                               .filter(member -> member.getHouseName().equals("Lannister"))
                               .count();
        System.out.println("There are " + total + " Lannisters");
    }


    /**
     * Print the names of any three Lannisters
     */
    @Test
    public void threeLannisters() {
    }

    /**
     * Print the names of the Lannisters as a comma-separated string
     */
    @Test
    public void lannisterNames() {
        String names = allMembers.stream()
                                 .filter(member -> member.getHouseName().equals("Lannister"))
                                 .map(Member::getName)
                                 .collect(joining(","));
        System.out.println(names);
    }

    /**
     * Who has the highest salary?
     */
    @Test
    public void highestSalary() {
        Optional<Member> max = allMembers.stream()
                                         .max(comparingDouble(Member::getSalary));
        System.out.println(max);
        max.ifPresent(System.out::println);
    }


    /**
     * Partition members into male and female
     * (note: women are LADY or QUEEN, men are everything else)
     */
    @Test
    public void menVsWomen() {
        Predicate<Member> queens = member -> member.getTitle().equals(Title.QUEEN);
        Predicate<Member> ladies = member -> member.getTitle().equals(Title.LADY);
        Map<Boolean, List<Member>> map = allMembers.stream()
                                                   .collect(partitioningBy(queens.or(ladies)));
        map.forEach((type, members) -> System.out.println(type + ": " + members));
    }

    /**
     * Group members into Houses
     */
    @Test
    public void membersByHouse() {
        Map<House, List<Member>> map = allMembers.stream()
                                                 .collect(groupingBy(Member::getHouse));
        map.forEach((house, list) -> {
            System.out.println(house);
            list.forEach(System.out::println);
        });
    }

    /**
     * How many members are in each house?
     * (group by house, downstream collector using count
     */
    @Test
    public void numberOfMembersByHouse() {
        Map<House, Long> map = allMembers.stream()
                                         .collect(groupingBy(Member::getHouse, counting()));
        map.forEach((house, size) -> System.out.println(house + ": " + size + " members"));
    }

    /**
     * Get the max, min, and ave salary for each house
     */
    @Test
    public void houseStats() {
        Map<House, DoubleSummaryStatistics> map = allMembers.stream()
                                                            .collect(groupingBy(Member::getHouse,
                                                                                summarizingDouble(Member::getSalary)));
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        map.forEach((house, stats) -> {
            System.out.println(house + " salary statistics");
            System.out.println("max: " + nf.format(stats.getMax()));
            System.out.println("min: " + nf.format(stats.getMin()));
            System.out.println("ave: " + nf.format(stats.getAverage()));
        });
        map.forEach((house,stats) -> System.out.println(house + ": " + stats));
    }
}
