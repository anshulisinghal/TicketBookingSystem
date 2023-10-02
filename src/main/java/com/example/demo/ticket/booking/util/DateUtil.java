package com.example.demo.ticket.booking.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static boolean validateInputDateFormat(String date) {
        boolean valid = true;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
            dtf.parse(date);
        } catch (DateTimeException ex) {
            valid = false;
        }
        return !valid;
    }

    public static boolean isBeforeTodaysDate(String date) {
        try {
            LocalDateTime parseDate = parseDate(date);
            return parseDate.toLocalDate().isBefore(LocalDate.now());
        } catch (DateTimeException ex){
            return false;
        }
    }

    public static String convertDateToString(LocalDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return date.format(dateTimeFormatter);
    }

    public static LocalDateTime parseDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
