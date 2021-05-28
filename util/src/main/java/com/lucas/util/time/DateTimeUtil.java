package com.lucas.util.time;

import java.time.*;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static LocalDateTime parseMilliTimestamp2UTCDateTime(Long timestamp) {
        return timestamp == null ? null : Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.UTC).toLocalDateTime();
    }

    public static LocalDateTime parseMilliTimestamp2LocalDateTime(Long timestamp, ZoneId zoneId) {
        return timestamp == null ? null : Instant.ofEpochMilli(timestamp).atZone(zoneId).toLocalDateTime();
    }

    public static Long parseUTCDateTime2MilliTimestamp(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    public static Long parseLocalDateTime2MilliTimestamp(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime == null ? null : localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    public static Long plusUTCDateTime2MilliTimestamp(LocalDateTime localDateTime, Long day) {
        return parseUTCDateTime2MilliTimestamp(localDateTime.plusDays(day));
    }

    public static Long compareMillTimestamp(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime).toDays();
    }
}
