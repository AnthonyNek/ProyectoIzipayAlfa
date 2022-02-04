package pe.izipay.common.beans.middlewares.mongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.types.DataStatusType;

class StatusTypeReadConverterTest {
    @Test
    void testConvert() {
        assertEquals(DataStatusType.NONE, (new StatusTypeReadConverter()).convert(0));
    }
}

