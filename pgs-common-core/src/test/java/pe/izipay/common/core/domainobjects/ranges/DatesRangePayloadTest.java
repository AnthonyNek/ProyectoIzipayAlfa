package pe.izipay.common.core.domainobjects.ranges;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Date;

import org.junit.jupiter.api.Test;

class DatesRangePayloadTest {
    
	@Test
    void testConstructor() {
        var actualDatesRangePayload = new DatesRangePayload<Date>();
        var range = new Range<Date>();
        range.setDesde(new Date());
        range.setHasta(new Date());
        actualDatesRangePayload.setRango_fechas(range);
        assertSame(range, actualDatesRangePayload.getRango_fechas());
    }
}

