package uk.co.modiber.prayertime.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeUtils {

	public static Calendar getStartOfTheWeek(Calendar calendar) {

		calendar.add(Calendar.DATE, -(calendar.get(Calendar.DAY_OF_WEEK) - 1));
		return calendar;
	}

	public static String formatCalendarDate(Calendar calendar, String pattern) {

		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(calendar.getTime());
	}
}
