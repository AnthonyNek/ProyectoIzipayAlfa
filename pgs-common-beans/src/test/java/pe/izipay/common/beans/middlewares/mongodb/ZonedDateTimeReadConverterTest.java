package pe.izipay.common.beans.middlewares.mongodb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ZonedDateTimeReadConverterTest {
    @Test
    void testConvert() {
        ZonedDateTimeReadConverter zonedDateTimeReadConverter = new ZonedDateTimeReadConverter();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Assertions.assertDoesNotThrow(() -> zonedDateTimeReadConverter.convert(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant())));
    }
}

