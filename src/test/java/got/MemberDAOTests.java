package got;

import org.junit.jupiter.api.Test;

import java.util.Collection;

public class MemberDAOTests {
    private final Collection<Member> allMembers = InMemoryMemberDAO.getInstance().getAll();

    /**
     * Find all members whose name starts with S and sort by id (natural sort)
     */
    @Test
    public void startWithS_sortByAlpha() {
    }

    /**
     * Final all Starks and sort them by name
     */
    @Test
    public void starks_alphaByName() {
    }

    /**
     * Find all members whose salary is less than 80K and sort by house
     */
    @Test
    public void salaryLessThan_sortByHouseName() {
    }

    /**
     * Sort members by house name, then by name
     */
    @Test
    public void sortByHouseName_sortByNameDesc() {
    }

    /**
     * Sort the Starks by birthdate
     */
    @Test
    public void starksByDob() {
    }

    /**
     * Find all Kings and sort by name in descending order
     */
    @Test
    public void kingsByNameDesc() {
    }

    /**
     * Find the average salary
     */
    @Test
    public void averageSalary() {
    }

    /**
     * Get the names of all the Starks, sorted in natural order
     * (note _names_, not members)
     */
    @Test
    public void namesSorted() {
    }

    /**
     * Are all the salaries greater than 100K?
     */
    @Test
    public void salariesGT100k() {
    }

    /**
     * Are there any members of House Greyjoy?
     */
    @Test
    public void greyjoys() {
    }

    /**
     * How many Lannisters are there?
     */
    @Test
    public void howManyLannisters() {
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
    }

    /**
     * Who has the highest salary?
     */
    @Test
    public void highestSalary() {
    }

    /**
     * Partition members into male and female
     * (note: women are LADY or QUEEN, men are everything else)
     */
    @Test
    public void menVsWomen() {
    }

    /**
     * Group members into Houses
     */
    @Test
    public void membersByHouse() {
    }

    /**
     * How many members are in each house?
     * (group by house, downstream collector using count
     */
    @Test
    public void numberOfMembersByHouse() {
    }

    /**
     * Get the max, min, and ave salary for each house
     */
    @Test
    public void houseStats() {
    }
}
