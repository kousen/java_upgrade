package got;

import got.House;
import got.InMemoryMemberDAO;
import got.Member;
import got.Title;
import org.junit.Test;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;

public class MemberDAOTests {
    private Collection<Member> allMembers = InMemoryMemberDAO.getInstance().getAll();

    /**
     * Find all members whose name starts with S and sort by id (natural sort)
     */
    @Test
    public void startWithS_sortByAlpha() {
        List<Member> members = allMembers.stream()
                .filter(m -> m.getName().startsWith("S"))
                .sorted()
                .collect(Collectors.toList());

        members.forEach(System.out::println);
    }

    /**
     * Final all Starks and sort them by name
     */
    @Test
    public void starks_alphaByName() {
        List<Member> members = allMembers.stream()
                .filter(member -> member.getHouse().getName().equals("Stark"))
                .sorted(Comparator.comparing(Member::getName))
                .collect(Collectors.toList());

        members.forEach(System.out::println);
    }

    /**
     * Find all members whose salary is less than 80K and sort by house
     */
    @Test
    public void salaryLessThan_sortByHouseName() {
        List<Member> members = allMembers.stream()
                .filter(member -> member.getSalary() < 80000)
                .sorted(Comparator.comparing(Member::getHouseName))
                .collect(Collectors.toList());

        members.forEach(System.out::println);
    }

    /**
     * Sort members by house name, then by name
     */
    @Test
    public void sortByHouseName_sortByNameDesc() {
        List<Member> members = allMembers.stream()
                .sorted(Comparator.comparing(Member::getHouseName)
                        .thenComparing(Member::getName))
                .collect(Collectors.toList());

        members.forEach(System.out::println);
    }

    /**
     * Sort the Starks by birthdate
     */
    @Test
    public void starksByDob() {
        List<Member> starks = allMembers.stream()
                .filter(member -> member.getHouseName().equals("Stark"))
                .sorted(Comparator.comparing(Member::getDob))
                .collect(Collectors.toList());

        starks.forEach(System.out::println);
    }

    /**
     * Find all Kings and sort by name in descending order
     */
    @Test
    public void kingsByNameDesc() {
        List<Member> members = allMembers.stream()
                .filter(member -> member.getTitle() == Title.KING)
                .sorted(Comparator.comparing(Member::getName).reversed())
                .collect(Collectors.toList());

        members.forEach(System.out::println);
    }

    /**
     * Find the average salary
     */
    @Test
    public void averageSalary() {
        OptionalDouble optionalAverage = allMembers.stream()
                .mapToDouble(Member::getSalary)
                .average();

        System.out.println(
                NumberFormat.getCurrencyInstance().format(
                        optionalAverage.orElse(0.0)));
    }

    /**
     * Get the names of all the Starks, sorted in natural order
     * (note _names_, not members)
     */
    @Test
    public void namesSorted() {
        List<String> names = allMembers.stream()
                .filter(member -> member.getHouseName().equals("Stark"))
                .map(Member::getName)
                .sorted()
                .collect(Collectors.toList());

        names.forEach(System.out::println);
    }

    /**
     * Are all the salaries greater than 100K?
     */
    @Test
    public void salariesGT100k() {
        boolean b = allMembers.stream()
                .allMatch(member -> member.getSalary() > 10000.0);

        assertFalse(b);
    }

    /**
     * Are there any members of House Greyjoy?
     */
    @Test
    public void greyjoys() {
        boolean b = allMembers.stream()
                .anyMatch(member -> member.getHouseName().equals("Greyjoy"));

        assertFalse(b);
    }

    /**
     * How many Lannisters are there?
     */
    @Test
    public void howManyLannisters() {
        long lannisters = allMembers.stream()
                .filter(member -> member.getHouseName().equals("Lannister"))
                .count();

        System.out.printf("There are %d members of House Lannister%n", lannisters);
    }

    /**
     * Print the names of any three Lannisters
     */
    @Test
    public void threeLannisters() {
        allMembers.stream()
                .filter(member -> member.getHouseName().equals("Lannister"))
                .limit(3)
                .map(Member::getName)
                .forEachOrdered(System.out::println);
    }

    /**
     * Print the names of the Lannisters as a comma-separated string
     */
    @Test
    public void lannisterNames() {
        String names = allMembers.stream()
                .filter(member -> member.getHouseName().equals("Lannister"))
                .map(Member::getName)
                .collect(Collectors.joining(","));

        System.out.println(names);
    }

    /**
     * Who has the highest salary?
     */
    @Test
    public void highestSalary() {
        Optional<Member> optional = allMembers.stream()
                .max(Comparator.comparingDouble(Member::getSalary));

        System.out.println(optional.orElse(null));
    }

    /**
     * Partition members into male and female
     * (note: women are LADY or QUEEN, men are everything else)
     */
    @Test
    public void menVsWomen() {
        Map<Boolean, List<Member>> map = allMembers.stream()
                .collect(Collectors.partitioningBy(member ->
                        member.getTitle() == Title.QUEEN ||
                                member.getTitle() == Title.LADY));

        map.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });
    }

    /**
     * Group members into Houses
     */
    @Test
    public void membersByHouse() {
        Map<House, List<Member>> map = allMembers.stream()
                .collect(Collectors.groupingBy(Member::getHouse));

        map.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });
    }

    /**
     * How many members are in each house?
     * (group by house, downstream collector using count
     */
    @Test
    public void numberOfMembersByHouse() {
        Map<House, Long> map = allMembers.stream()
                .collect(Collectors.groupingBy(Member::getHouse,
                        Collectors.counting()));

        map.forEach((k, v) -> System.out.println(k + " has " + v + " members"));
    }

    /**
     * Get the max, min, and ave salary for each house
     */
    @Test
    public void houseStats() {
        Map<House, DoubleSummaryStatistics> map = allMembers.stream()
                .collect(Collectors.groupingBy(Member::getHouse,
                        Collectors.summarizingDouble(Member::getSalary)));

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        System.out.printf("%13s %14s %15s %15s%n", "House", "Max", "Min", "Ave");
        map.forEach((k, v) ->
                System.out.printf("%15s %15s %15s %15s%n", k,
                        nf.format(v.getMax()),
                        nf.format(v.getMin()),
                        nf.format(v.getAverage()))
        );
    }
}
