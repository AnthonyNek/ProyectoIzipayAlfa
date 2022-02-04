package pe.izipay.common.core.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

class DateTimeHelperTest {
    @Test
    void testFormatISO() {
        assertEquals("1970-01-02", DateTimeHelper.formatISO(LocalDate.ofEpochDay(1L)));
        assertEquals("0001-01-01T01:01:00", DateTimeHelper.formatISO(LocalDateTime.of(1, 1, 1, 1, 1)));
        assertEquals("01:01:00", DateTimeHelper.formatISO(LocalTime.of(1, 1)));
    }

    @Test
    void testFormatISO2() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertEquals("1970-01-01T00:00:00.000Z", DateTimeHelper
                .formatISO(Date.from(atStartOfDayResult.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant())));
    }

    @Test
    void testFormatISO3() {
        java.sql.Date date = mock(java.sql.Date.class);
        when(date.getTime()).thenReturn(10L);
        assertEquals("1970-01-01T00:00:00.010Z", DateTimeHelper.formatISO(date));
        verify(date).getTime();
    }

    @Test
    void testFormatJsISO() {
        assertEquals("0001-01-01T01:01:00.000Z", DateTimeHelper.formatJsISO(LocalDateTime.of(1, 1, 1, 1, 1)));
    }

    @Test
    void testFormatISODate() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertEquals("1970-01-01", DateTimeHelper
                .formatISODate(Date.from(atStartOfDayResult.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant())));
    }

    @Test
    void testFormatISODate2() {
        java.sql.Date date = mock(java.sql.Date.class);
        when(date.getTime()).thenReturn(10L);
        assertEquals("1970-01-01", DateTimeHelper.formatISODate(date));
        verify(date).getTime();
    }

    @Test
    void testIsWithinRange() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date value = Date.from(atStartOfDayResult.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date start = Date.from(atStartOfDayResult1.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(DateTimeHelper.isWithinRange(value, start,
                Date.from(atStartOfDayResult2.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant())));
    }

    @Test
    void testIsWithinRange2() {
        LocalDateTime atStartOfDayResult = LocalDate.of(0, 1, 1).atStartOfDay();
        Date value = Date.from(atStartOfDayResult.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date start = Date.from(atStartOfDayResult1.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertFalse(DateTimeHelper.isWithinRange(value, start,
                Date.from(atStartOfDayResult2.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant())));
    }

    @Test
    void testIsWithinRange3() {
        java.sql.Date date = mock(java.sql.Date.class);
        when(date.before(any())).thenReturn(true);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        java.util.Date start = java.util.Date
                .from(atStartOfDayResult.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertFalse(DateTimeHelper.isWithinRange(date, start,
                java.util.Date.from(atStartOfDayResult1.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant())));
        verify(date).before(any());
    }

    @Test
    void testIsWithinRange4() {
        java.sql.Date date = mock(java.sql.Date.class);
        when(date.after(any())).thenReturn(true);
        when(date.before(any())).thenReturn(false);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        java.util.Date start = java.util.Date
                .from(atStartOfDayResult.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertFalse(DateTimeHelper.isWithinRange(date, start,
                java.util.Date.from(atStartOfDayResult1.atZone(ZoneId.of(DateTimeHelper.TIME_ZONE)).toInstant())));
        verify(date).after(any());
        verify(date).before(any());
    }

    @Test
    void testConvertToDate() {
        Date actualDate = DateTimeHelper.convertToDate(LocalDate.ofEpochDay(1L));
        assertEquals("1970-01-02", (new SimpleDateFormat(DateTimeHelper.FORMAT_ISO_DATE)).format(actualDate));
    }

    @Test
    void testConvertToDate2() {
        Date actualDate = DateTimeHelper.convertToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        assertEquals("0001-01-03", (new SimpleDateFormat(DateTimeHelper.FORMAT_ISO_DATE)).format(actualDate));
    }

    @Test
    void testCreateDateFrom() {
        assertEquals("0001-01-01", DateTimeHelper.createDateFrom(LocalDateTime.of(1, 1, 1, 1, 1)).toString());
    }

    @Test
    void testCreateTimeFrom() {
        assertEquals("01:01", DateTimeHelper.createTimeFrom(LocalDateTime.of(1, 1, 1, 1, 1)).toString());
    }
}

