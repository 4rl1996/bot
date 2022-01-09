package com.example.telebot.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class EduBotUtils {

    public static ZonedDateTime timeConverter(Integer date) {
        ZonedDateTime messageTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(date), ZoneId.of("Europe/Moscow"));
        String parsedTime = messageTime.format(DateTimeFormatter.ISO_DATE_TIME);
        return ZonedDateTime.parse(parsedTime);
    }
}
