package datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class AntarcticaTimeZones {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        List<ZonedDateTime> antarcticZones =
                ZoneId.getAvailableZoneIds().stream()  // Stream<String>
                        .filter(regionId -> regionId.contains("Antarctica"))
                        .map(ZoneId::of)  // Stream<ZoneId>
                        .map(now::atZone) // Stream<ZonedDateTime>
                        .sorted(comparingInt(
                                zoneId -> zoneId.getOffset().getTotalSeconds()))
                        .toList();

        antarcticZones.forEach(zdt ->
                System.out.printf("UTC%6s: %25s %7s%n", zdt.getOffset(), zdt.getZone(),
                        zdt.getZone().getRules().isDaylightSavings(zdt.toInstant())));
    }
}
