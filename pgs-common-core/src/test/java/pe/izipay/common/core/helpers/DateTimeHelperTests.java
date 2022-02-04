package pe.izipay.common.core.helpers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootConfiguration
@SpringBootTest
class DateTimeHelperTests {	
	
	@Test
	void toISODateTime() {
		var zone = ZoneId.of("America/La_Paz");
		var zonedDateTime = ZonedDateTime.of(2021, 7, 20, 17,
				46, 3, 0, zone);
		var localDate = LocalDate.of(2021, 7, 20);
		var localTime = LocalTime.of(17, 46, 3, 0);
		var localDateTime = LocalDateTime.of(2021, 7, 20,
				17, 46, 3, 0);

		var expectedZonedDateTime = "2021-07-20T21:46:03.000Z";
		var expectedLocalDate = "2021-07-20";
		var expectedLocalTime = "17:46:03";
		var expectedLocalDateTime = "2021-07-20T17:46:03";
		var expectedConverted = ZonedDateTime.of(2021, 7, 21,
				12, 25, 3, 927000000, DateTimeHelper.ZONE_ID);

		var resultZonedDateTime = DateTimeHelper.formatISO(zonedDateTime);
		var resultLocalDate = DateTimeHelper.formatISO(localDate);
		var resultLocalTime = DateTimeHelper.formatISO(localTime);
		var resultLocalDateTime = DateTimeHelper.formatISO(localDateTime);
		var resultNow = DateTimeHelper.nowISO();
		var converted = DateTimeHelper.convertFromISO("2021-07-21T12:25:03.927Z");

		assertThat(resultZonedDateTime).isEqualTo(expectedZonedDateTime);
		assertThat(resultLocalDate).isEqualTo(expectedLocalDate);
		assertThat(resultLocalTime).isEqualTo(expectedLocalTime);
		assertThat(resultLocalDateTime).isEqualTo(expectedLocalDateTime);
		assertThat(resultNow).containsPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z");
		assertThat(converted).isEqualTo(expectedConverted);
	}
	
	@Test
	void convertToDate() {
		var date1 = DateTimeHelper.convertToLocalDate(new Date());
		var date2 = DateTimeHelper.convertToDate(LocalDate.now());
		var date3 = DateTimeHelper.convertToLocalDate(date2);
		var date4 = DateTimeHelper.convertToDate(LocalDateTime.now());
		log.info(DateTimeHelper.formatISO(date1));
		log.info(date2.toString());
		log.info(DateTimeHelper.formatISO(date2));
		log.info(DateTimeHelper.formatISODate(date2));
		log.info(DateTimeHelper.formatISO(date3));
		log.info(DateTimeHelper.formatISO(date4));
		
		assertThat(date1).isNotNull();		
		assertThat(date2).isNotNull();
		assertThat(date3).isNotNull();
		assertThat(date4).isNotNull();
								
		assertThat(date1.isEqual(date3)).isTrue();
	}
	
	@Test
	void getFirstTimeOfDay() {
		var dateISO = DateTimeHelper.formatJsISO(DateTimeHelper.getFirstTimeOfDay(LocalDate.of(2021, 12, 1)));
		log.info(dateISO);
		assertThat(dateISO).isEqualTo("2021-12-01T00:00:00.000Z");
	}
	
	@Test
	void getLastTimeOfDay() {				
		var dateISO = DateTimeHelper.formatJsISO(DateTimeHelper.getLastTimeOfDay(LocalDate.of(2021, 12, 1)));
		log.info(dateISO);
		assertThat(dateISO).isEqualTo("2021-12-01T23:59:59.999Z");
	}
	@Test
	void testIsWithoutRange() {
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		Date value = Date.from(atStartOfDayResult.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant());
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		Date start = Date.from(atStartOfDayResult1.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant());
		LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
		assertFalse(DateTimeHelper.isWithoutRange(value, start,
				Date.from(atStartOfDayResult2.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant())));
	}

	@Test
	void testIsWithoutRange2() {
		LocalDateTime atStartOfDayResult = LocalDate.of(0, 1, 1).atStartOfDay();
		Date value = Date.from(atStartOfDayResult.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant());
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		Date start = Date.from(atStartOfDayResult1.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant());
		LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
		assertTrue(DateTimeHelper.isWithoutRange(value, start,
				Date.from(atStartOfDayResult2.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant())));
	}
}
