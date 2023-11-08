package got;

import java.util.*;

public interface MemberDAO {
    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAllByHouse(House house);

    Collection<Member> getAll();

    List<Member> startWithSandSortAlphabetically();

    List<Member> lannisters_alphabeticallyByName();

    List<Member> salaryLessThanAndSortByHouse(double max);

    List<Member> sortByHouseNameThenSortByNameDesc();

    List<Member> houseByDob(House house);

    List<Member> kingsByNameDesc();

    double averageSalary();

    List<String> namesSorted(House house);

    boolean salariesGreaterThan(double max);

    boolean anyMembers(House house);

    long howMany(House house);

    String houseMemberNames(House house);

    Optional<Member> highestSalary();

    Map<Boolean, List<Member>> royaltyPartition();

    Map<House, List<Member>> membersByHouse();

    Map<House, Long> numberOfMembersByHouse();

    Map<House, DoubleSummaryStatistics> houseStats();
}
