package com.lucas.demo.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Lucasfen
 * @Date 2021/05/09
 */
public class TimeUtils {

    public static LocalDateTime timeStampToLocalDateTime(Long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp/1000, 0, ZoneOffset.ofHours(0));
    }
}
