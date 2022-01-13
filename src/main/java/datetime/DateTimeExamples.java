package datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class DateTimeExamples {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now);

        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        System.out.println(ZoneId.getAvailableZoneIds().size());

        LocalDate then = now.plusDays(3);
        System.out.println(then);
        ZoneId.getAvailableZoneIds().stream()
                .filter(zoneId -> zoneId.contains("Asia"))
                .sorted()
                .forEach(System.out::println);
    }
}
