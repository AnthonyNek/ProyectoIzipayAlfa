package pe.izipay.common.core.helpers;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeHelper {

	public static final String TIME_ZONE = "UTC";
	public static final String FORMAT_ISO_DATE = "yyyy-MM-dd";
	public static final String FORMAT_ISO_JAVASCRIPT =  FORMAT_ISO_DATE + "'T'HH:mm:ss.SSS'Z'";		

	public static final DateTimeFormatter ISO_JAVASCRIPT_DATE_TIME;
	
	public static final SimpleDateFormat SDF_JAVASCRIPT_ISO;
	public static final SimpleDateFormat SDF_DATE_ISO;
	public static final ZoneId ZONE_ID;

	static {					
		ZONE_ID = ZoneId.of(TIME_ZONE);
		ISO_JAVASCRIPT_DATE_TIME = DateTimeFormatter.ofPattern(FORMAT_ISO_JAVASCRIPT)
				.withZone(ZONE_ID);
		
		var timeZone = TimeZone.getTimeZone(TIME_ZONE);
		SDF_JAVASCRIPT_ISO = new SimpleDateFormat(FORMAT_ISO_JAVASCRIPT);
		SDF_JAVASCRIPT_ISO.setTimeZone(timeZone);
		
		SDF_DATE_ISO = new SimpleDateFormat(FORMAT_ISO_DATE);
		SDF_DATE_ISO.setTimeZone(timeZone);
	}
	
	public static String formatISO(LocalDateTime value) {
		return value.format(DateTimeFormatter.ISO_DATE_TIME);
	}
	
	public static String formatJsISO(LocalDateTime value) {
		return value.format(ISO_JAVASCRIPT_DATE_TIME);
	}

	public static String formatISO(LocalDate value) {
		return value.format(DateTimeFormatter.ISO_DATE);
	}

	public static String formatISO(LocalTime value) {
		return value.format(DateTimeFormatter.ISO_TIME);
	}

	public static String formatISO(ZonedDateTime value) {
		return value.format(ISO_JAVASCRIPT_DATE_TIME);
	}		
	
	public static String formatISO(Date value) {
		return SDF_JAVASCRIPT_ISO.format(value);
	}
	
	public static String formatISODate(Date value) {
		return SDF_DATE_ISO.format(value);
	}

	public static String nowISO() {
		return ZonedDateTime.now().format(ISO_JAVASCRIPT_DATE_TIME);
	}

	public static ZonedDateTime convertFromISO(String value) {
		return ZonedDateTime.parse(value, ISO_JAVASCRIPT_DATE_TIME);
	}

	public static boolean isWithoutRange(Date value, Date start, Date end) {
		return value.before(start) || value.after(end);
	}

	public static boolean isWithinRange(Date value, Date start, Date end) {
		return !isWithoutRange(value, start, end);
	}	
	
	public static LocalDate convertToLocalDate(Date value) {
		return value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static LocalDateTime convertToLocalDateTime(Date value) {
		return value.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
	}

	public static Date convertToDate(LocalDate value) {		
		return Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant());		
	}
	
	public static Date convertToDate(LocalDateTime value) {				
		return Date.from(value.atZone(ZoneId.systemDefault()).toInstant());		
	}

	public static LocalDateTime getFirstTimeOfDay(LocalDate value) {
		return LocalDateTime.of(value, LocalTime.of(0, 0, 0, 0));
	}
	
	public static LocalDateTime getLastTimeOfDay(LocalDate value) {
		return LocalDateTime.of(value, LocalTime.of(23, 59, 59, 999999999));
	}
	
	public static LocalDate createDateFrom(LocalDateTime value) {
		return LocalDate.of(value.getYear(), value.getMonth(), value.getDayOfMonth());
	}
	
	public static LocalTime createTimeFrom(LocalDateTime value) {
		return LocalTime.of(value.getHour(), value.getMinute(), value.getSecond());
	}
	
}