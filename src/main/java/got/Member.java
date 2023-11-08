package got;

import java.time.LocalDate;

public record Member(
        Long id,
        Title title,
        String name,
        LocalDate dob,
        double salary,
        House house
) implements Comparable<Member> {

    @Override
    public int compareTo(Member member) {
        return id.compareTo(member.id);
    }
}
