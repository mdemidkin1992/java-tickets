import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeUtil {
    private static final Map<String, String> ZONE_ID_MAP = Map.of(
            "VVO", "Asia/Vladivostok",
            "UFA", "Asia/Yekaterinburg",
            "LRN", "Asia/Nicosia",
            "TLV", "Asia/Jerusalem"
    );

    public static ZonedDateTime toZonedDateTime(LocalDate date, LocalTime time, String zone) {
        LocalDateTime localDateTime = date.atTime(time);
        ZoneId zoneId = ZoneId.of(ZONE_ID_MAP.getOrDefault(zone, "UTC"));
        return localDateTime.atZone(zoneId);
    }
}
