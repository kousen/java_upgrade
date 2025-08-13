package got;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMemberDAO implements MemberDAO {
    private static final MemberDAO dao = new InMemoryMemberDAO();

    private final List<Member> allMembers = Arrays.asList(
            new Member(1L, Title.LORD, "Eddard", LocalDate.of(1959, Month.APRIL, 17), 100000.0, House.STARK),
            new Member(2L, Title.LADY, "Catelyn", LocalDate.of(1964, Month.JANUARY, 17), 80000.0, House.STARK),
            new Member(3L, Title.LADY, "Arya", LocalDate.of(1997, Month.APRIL, 15), 50000.0, House.STARK),
            new Member(4L, Title.LADY, "Sansa", LocalDate.of(1996, Month.FEBRUARY, 21), 60000.0, House.STARK),
            new Member(5L, Title.SIR, "Bran", LocalDate.of(1999, Month.APRIL, 9), 10000.0, House.STARK),
            new Member(6L, Title.KING, "Robb", LocalDate.of(1986, Month.JUNE, 18), 100000.0, House.STARK),
            new Member(7L, Title.KING, "Jon", LocalDate.of(1986, Month.DECEMBER, 26), 90000.0, House.SNOW),
            new Member(8L, Title.SIR, "Jaime", LocalDate.of(1970, Month.JULY, 27), 120000.0, House.LANNISTER),
            new Member(9L, Title.LORD, "Tyrion", LocalDate.of(1969, Month.JUNE, 11), 70000.0, House.LANNISTER),
            new Member(10L, Title.LORD, "Tywin", LocalDate.of(1946, Month.OCTOBER, 10), 200000.0, House.LANNISTER),
            new Member(11L, Title.LADY, "Cersei", LocalDate.of(1973, Month.OCTOBER, 3), 120000.0, House.LANNISTER),
            new Member(12L, Title.QUEEN, "Daenerys", LocalDate.of(1987, Month.MAY, 1), 130000.0, House.TARGARYEN),
            new Member(13L, Title.LORD, "Viserys", LocalDate.of(1983, Month.NOVEMBER, 17), 100000.0, House.TARGARYEN),
            new Member(14L, Title.KING, "Robert", LocalDate.of(1964, Month.JANUARY, 14), 180000.0, House.BARATHEON),
            new Member(15L, Title.KING, "Joffrey", LocalDate.of(1992, Month.MAY, 20), 100000.0, House.BARATHEON),
            new Member(16L, Title.KING, "Tommen", LocalDate.of(1997, Month.SEPTEMBER, 7), 60000.0, House.BARATHEON),
            new Member(17L, Title.KING, "Stannis", LocalDate.of(1957, Month.MARCH, 27), 123456.0, House.BARATHEON),
            new Member(18L, Title.QUEEN, "Margaery", LocalDate.of(1982, Month.FEBRUARY, 11), 80000.0, House.TYRELL),
            new Member(19L, Title.SIR, "Loras", LocalDate.of(1988, Month.MARCH, 24), 70000.0, House.TYRELL),
            new Member(20L, Title.LADY, "Olenna", LocalDate.of(1938, Month.JULY, 20), 130000.0, House.TYRELL),
            new Member(21L, Title.LORD, "Roose", LocalDate.of(1963, Month.SEPTEMBER, 12), 100000.0, House.BOLTON),
            new Member(22L, Title.LORD, "Ramsay", LocalDate.of(1985, Month.MAY, 13), 140000.0, House.BOLTON)
    );

    private InMemoryMemberDAO() {}

    public static MemberDAO getInstance() {
        return dao;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return allMembers.stream()
                .filter(member -> member.id().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return allMembers.stream()
                .filter(member -> member.name().equals(name))
                .findFirst();
    }

    @Override
    public List<Member> findAllByHouse(House house) {
        return allMembers.stream()
                .filter(member -> member.house().equals(house))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Member> getAll() {
        return Collections.unmodifiableCollection(allMembers);
    }

    @Override
    public List<Member> startWithSandSortAlphabetically() {
        return List.of();
    }

    @Override
    public List<Member> lannisters_alphabeticallyByName() {
        return List.of();
    }

    @Override
    public List<Member> salaryLessThanAndSortByHouse(double max) {
        return List.of();
    }

    @Override
    public List<Member> sortByHouseNameThenSortByNameDesc() {
        return List.of();
    }

    @Override
    public List<Member> houseByDob(House house) {
        return List.of();
    }

    @Override
    public List<Member> kingsByNameDesc() {
        return List.of();
    }

    @Override
    public double averageSalary() {
        return 0;
    }

    @Override
    public List<String> namesSorted(House house) {
        return List.of();
    }

    @Override
    public boolean salariesGreaterThan(double max) {
        return false;
    }

    @Override
    public boolean anyMembers(House house) {
        return false;
    }

    @Override
    public long howMany(House house) {
        return 0;
    }

    @Override
    public String houseMemberNames(House house) {
        return "";
    }

    @Override
    public Optional<Member> highestSalary() {
        return Optional.empty();
    }

    @Override
    public Map<Boolean, List<Member>> royaltyPartition() {
        return Map.of();
    }

    @Override
    public Map<House, List<Member>> membersByHouse() {
        return Map.of();
    }

    @Override
    public Map<House, Long> numberOfMembersByHouse() {
        return Map.of();
    }

    @Override
    public Map<House, DoubleSummaryStatistics> houseStats() {
        return Map.of();
    }
}
