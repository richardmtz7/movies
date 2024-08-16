package com.masiv.movies.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
	private static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(ISO_8601_DATE_FORMAT);
    static {
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    public static String toIso8601String(Date date) {
        return DATE_FORMAT.format(date);
    }
    public static Date parseIso8601Date(String dateString) throws ParseException {
        return DATE_FORMAT.parse(dateString);
    }
    public static String getCurrentIso8601Date() {
        return toIso8601String(new Date());
    }    
}
