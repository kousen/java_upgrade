package datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static java.util.Comparator.comparingInt;

/*
 * Alternative equivalent sorts:
 * .sorted((zoneId1, zoneId2) ->
 *      (int) Duration.between(now.atZone(zoneId1), now.atZone(zoneId2)).getSeconds())
 *  -- or --
 * .sorted((zoneId1, zoneId2) ->
 *      (int) ChronoUnit.MINUTES.between(now.atZone(zoneId1), now.atZone(zoneId2))
 */
public class FunnyOffsets {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        ZonedDateTime current = instant.atZone(ZoneId.systemDefault());
        // equivalent to ZonedDateTime.now()
        System.out.printf("Current time is %s%n%n", current);

        System.out.printf("%10s %20s %13s%n", "Offset", "ZoneId", "Time");
        ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of) // Stream<ZoneId>>
                .filter(zoneId -> {
                    ZoneOffset offset = instant.atZone(zoneId).getOffset();
                    return offset.getTotalSeconds() % (60 * 60) != 0;
                })
                .sorted(comparingInt(zoneId ->
                        instant.atZone(zoneId).getOffset().getTotalSeconds()))
                .forEach(zoneId -> {
                    ZonedDateTime zdt = current.withZoneSameInstant(zoneId);
                    System.out.printf("%10s %25s %10s%n", zdt.getOffset(), zoneId,
                            zdt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
                });
    }
}
