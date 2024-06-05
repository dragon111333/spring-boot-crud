package com.testsdemo.testcrud.util;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateTimeUtil {

    private static final Map<String, String> fullMonthNameMap;

    public static final String BANGKOK_TIME_ZONE = "Asia/Bangkok";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    public static final String FULL_DATE_FORMAT = "d MMMM yyyy";
    public static final String DATABASE_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATABASE_DATETIME_WITH_NO_SSS = "yyyy-MM-dd HH:mm:ss";
    public static final String SIMPLE_DATE_FORMAT_START_WITH_DAY = "dd/MM/yyyy";

    private static final DateTimeParser[] parsers = {
            ISODateTimeFormat.dateTime().getParser(),
            ISODateTimeFormat.dateTimeNoMillis().getParser(),
            DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT).getParser(),
            DateTimeFormat.forPattern(DATABASE_DATETIME_WITH_NO_SSS).getParser(),
            DateTimeFormat.forPattern(DATABASE_DATETIME_FORMAT).getParser(),
            DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT_START_WITH_DAY).getParser()
    };

    private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter();

    static {
        fullMonthNameMap = new HashMap<>();
        fullMonthNameMap.put("January", "มกราคม");
        fullMonthNameMap.put("February", "กุมภาพันธ์");
        fullMonthNameMap.put("March", "มีนาคม");
        fullMonthNameMap.put("April", "เมษายน");
        fullMonthNameMap.put("May", "พฤษภาคม");
        fullMonthNameMap.put("June", "มิถุนายน");
        fullMonthNameMap.put("July", "กรกฎาคม");
        fullMonthNameMap.put("August", "สิงหาคม");
        fullMonthNameMap.put("September", "กันยายน");
        fullMonthNameMap.put("October", "ตุลาคม");
        fullMonthNameMap.put("November", "พฤศจิกายน");
        fullMonthNameMap.put("December", "ธันวาคม");
    }

    private DateTimeUtil() {
        throw new IllegalStateException("Utility Class");
    }

    public static String toDateString(Date date) {
        return toDateString(date, YYYYMMDD);
    }

    public static String toDateString(Date date, String pattern) {
        DateTime dateTime = new DateTime(date);
        return toDateTimeString(dateTime.withZone(getBangkokTimeZone()), pattern);
    }

    public static String toDateTimeString(DateTime dateTime) {
        return toDateTimeString(dateTime, YYYYMMDDHHMMSS);
    }

    public static String toDateTimeString(DateTime dateTime, String pattern) {
        if (null != dateTime) {
            return dateTime.toString(pattern);
        } else {
            return "";
        }
    }

    public static String toThaiDateString(Date date, String pattern) {
        DateTime dateTime = new DateTime(date).withZone(getBangkokTimeZone());
        return toDateTimeString(dateTime.withChronology(BuddhistChronology.getInstance(getBangkokTimeZone())), pattern);
    }

    public static String toThaiFullDateString(Date date) {
        String fullDateStr = toThaiDateString(date, FULL_DATE_FORMAT);
        return fullMonthNameMap
                .keySet()
                .stream()
                .filter(fullDateStr::contains)
                .findFirst()
                .map(key -> fullDateStr.replace(key, fullMonthNameMap.get(key)))
                .orElse(fullDateStr);
    }

    public static String getCurrentDateTimeString() {
        return DateTime.now().withZone(getBangkokTimeZone()).toString(YYYYMMDDHHMMSS);
    }

    public static DateTime getCurrentDateTime() {
        return DateTime.now().withZone(getBangkokTimeZone());
    }

    public static DateTimeZone getBangkokTimeZone() {
        return DateTimeZone.forID(BANGKOK_TIME_ZONE);
    }

    public static DateTime getCurrentDate() {
        return toDateTime(toDateTimeString(getCurrentDateTime(), YYYYMMDD), YYYYMMDD);
    }

    public static DateTime toDateTime(String txt) {
        return StringUtils.isEmpty(txt) ? null : formatter.parseDateTime(txt);
    }

    public static LocalDateTime toLocalDateTime(String txt) {
        if (StringUtils.isEmpty(txt)) {
            return null;
        } else {
            return formatter.parseLocalDateTime(txt);
        }
    }

    public static DateTime toDateTime(String dateTimeStr, String dateTimeFormat) {
        if (StringUtils.isEmpty(dateTimeFormat)) {
            return DateTimeFormat.forPattern(YYYYMMDDHHMMSS).parseDateTime(dateTimeStr);
        } else {
            return DateTimeFormat.forPattern(dateTimeFormat).parseDateTime(dateTimeStr);
        }
    }

    public static Date toDateISO8601(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        return new DateTime(strDate).toDate();
    }

    public static Date toDate(String dateTimeStr, String dateTimeFormat) {
        return toDateTime(dateTimeStr, dateTimeFormat).toDate();
    }

    public static Date toDate(String txt) {
        return StringUtils.isEmpty(txt) ? null : formatter.parseDateTime(txt).toDate();
    }

    public static Date toDate(String txt, String dateTimeFormat, Chronology chronology) {
        return DateTimeFormat.forPattern(dateTimeFormat).withChronology(chronology).parseDateTime(txt).toDate();
    }

    public static Date addDays(Date src, int days) {
        return new DateTime(src).plusDays(days).toDate();
    }

    public static int compare(Date d1, Date d2) {
        DateTime date1 = new DateTime(d1);
        DateTime date2 = new DateTime(d2);
        return date1.compareTo(date2);
    }
}