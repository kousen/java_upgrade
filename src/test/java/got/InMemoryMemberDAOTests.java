package got;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class InMemoryMemberDAOTests {
    private final MemberDAO dao = new InMemoryMemberDAO();

    @Test
    void findById() {
        Optional<Member> member = dao.findById(1L);
        assertThat(member).isPresent();
        assertThat(member.get().name()).isEqualTo("Eddard");
    }

    @Test
    void findById_notFound() {
        Optional<Member> member = dao.findById(100L);
        assertThat(member).isEmpty();
    }

    @Test
    void findByName() {
        Optional<Member> member = dao.findByName("Eddard");
        assertThat(member).isPresent();
        assertThat(member.get().id()).isEqualTo(1L);
    }

    @Test
    void findByName_notFound() {
        Optional<Member> member = dao.findByName("Ned");
        assertThat(member).isEmpty();
    }

    @Test
    void findAllByHouse() {
        List<Member> members = dao.findAllByHouse(House.STARK);
        assertThat(members).hasSize(6)
                .allMatch(member -> member.house() == House.STARK);
    }

    @Test
    void findAllByHouse_notFound() {
        List<Member> members = dao.findAllByHouse(House.GREYJOY);
        assertThat(members).isEmpty();
    }

    @Test
    void getAll() {
        Collection<Member> members = dao.getAll();
        assertThat(members).hasSize(22);
    }


    @Test
    public void startWithS_sortAlphabetically() {
        List<Member> members = dao.startWithSandSortAlphabetically();

        assertAll(
                () -> assertThat(members.get(0).name()).isEqualTo("Sansa"),
                () -> assertThat(members.get(1).name()).isEqualTo("Stannis")
        );
    }

    @Test
    public void lannisters_alphabeticallyByName() {
        List<Member> members = dao.lannisters_alphabeticallyByName();

        List<String> names = members.stream()
                .map(Member::name)
                .toList();

        assertThat(names).containsExactly("Cersei", "Jaime", "Tyrion", "Tywin");
    }

    @Test
    public void salaryLessThan_sortByHouse() {
        List<Member> members = dao.salaryLessThanAndSortByHouse(80000.0);

        assertThat(members).hasSize(6)
                .allMatch(member -> member.salary() < 80000.0);

        List<House> houses = members.stream()
                .map(Member::house)
                .distinct()
                .toList();

        assertThat(houses).containsExactly(
                House.BARATHEON, House.LANNISTER, House.STARK, House.TYRELL);
    }

    @Test
    public void sortByHouseName_sortByNameDesc() {
        List<Member> members = dao.sortByHouseNameThenSortByNameDesc();

        assertThat(members).hasSize(22);
    }

    @Test
    public void starksByDob() {
        List<Member> members = dao.houseByDob(House.STARK);

        assertThat(members).hasSize(6)
                .allMatch(member -> member.house() == House.STARK);
    }

    @Test
    public void kingsByNameDesc() {
        List<Member> members = dao.kingsByNameDesc();

        assertThat(members).hasSize(6)
                .allMatch(member -> member.title() == Title.KING);
    }

    @Test
    public void averageSalary() {
        double averageSalary = dao.averageSalary();

        assertThat(averageSalary).isCloseTo(100611.64, within(0.1));
    }

    @Test
    public void namesSorted() {
        List<String> names = dao.namesSorted(House.STARK);

        assertThat(names).hasSize(6)
                .containsExactly("Arya", "Bran", "Catelyn", "Eddard", "Robb", "Sansa");
    }

    @Test
    public void salariesGT100k() {
        assertThat(dao.salariesGreaterThan(100000.0)).isTrue();
    }

    @Test
    public void greyjoys() {
        assertThat(dao.anyMembers(House.GREYJOY)).isFalse();
    }

    @Test
    public void howManyLannisters() {
        long count = dao.howMany(House.LANNISTER);
        assertThat(count).isEqualTo(4);
    }

    @Test
    public void lannisterNames() {
        String lannisterNames = dao.houseMemberNames(House.LANNISTER);
        assertThat(lannisterNames).isEqualTo("Jaime, Tyrion, Tywin, Cersei");
    }

    @Test
    public void highestSalary() {
        Optional<Member> member = dao.highestSalary();

        assertThat(member).isPresent();
        assertThat(member.get().name()).isEqualTo("Tywin");
    }

    @Test
    public void royalty_or_not() {
        Map<Boolean, List<Member>> map = dao.royaltyPartition();
        assertAll(
                () -> assertThat(map.get(true)).hasSize(8),
                () -> assertThat(map.get(false)).hasSize(14)
        );
    }

    @Test
    public void membersByHouse() {
        Map<House, List<Member>> houseListMap = dao.membersByHouse();
        assertAll(
                () -> assertThat(houseListMap.get(House.STARK)).hasSize(6),
                () -> assertThat(houseListMap.get(House.LANNISTER)).hasSize(4),
                () -> assertThat(houseListMap.get(House.TARGARYEN)).hasSize(2),
                () -> assertThat(houseListMap.get(House.BARATHEON)).hasSize(4),
                () -> assertThat(houseListMap.get(House.TYRELL)).hasSize(3),
                () -> assertThat(houseListMap.get(House.BOLTON)).hasSize(2),
                () -> assertThat(houseListMap.get(House.SNOW)).hasSize(1)
        );
    }

    @Test
    public void numberOfMembersByHouse() {
        Map<House, Long> memberCountByHouse = dao.numberOfMembersByHouse();
        assertAll(
                () -> assertThat(memberCountByHouse.get(House.STARK)).isEqualTo(6),
                () -> assertThat(memberCountByHouse.get(House.LANNISTER)).isEqualTo(4),
                () -> assertThat(memberCountByHouse.get(House.TARGARYEN)).isEqualTo(2),
                () -> assertThat(memberCountByHouse.get(House.BARATHEON)).isEqualTo(4),
                () -> assertThat(memberCountByHouse.get(House.TYRELL)).isEqualTo(3),
                () -> assertThat(memberCountByHouse.get(House.BOLTON)).isEqualTo(2),
                () -> assertThat(memberCountByHouse.get(House.SNOW)).isEqualTo(1)
        );

    }

    @Test
    public void houseStats() {
        Map<House, DoubleSummaryStatistics> stats = dao.houseStats();
        assertAll(
                () -> assertThat(stats.get(House.STARK).getMax()).isEqualTo(100000.0),
                () -> assertThat(stats.get(House.STARK).getMin()).isEqualTo(10000.0),
                () -> assertThat(stats.get(House.STARK).getAverage())
                        .isCloseTo(66666.66, withinPercentage(0.01))
        );
    }
}
