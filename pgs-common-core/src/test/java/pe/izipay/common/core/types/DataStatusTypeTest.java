package pe.izipay.common.core.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataStatusTypeTest {
    @Test
    void testConvert() {
        assertEquals(DataStatusType.ENABLED, DataStatusType.convert(true));
        assertEquals(DataStatusType.DISABLED, DataStatusType.convert(false));
    }

    @Test
    void testConvertToNumber() {
        assertEquals(1, DataStatusType.convertToNumber(true));
        assertEquals(0, DataStatusType.convertToNumber(null));
        assertEquals(2, DataStatusType.convertToNumber(false));
    }

    @Test
    void testMatch() {
        Assertions.assertFalse(DataStatusType.NONE.match((byte) 'A'));
        Assertions.assertTrue(DataStatusType.NONE.match((byte) 0));
    }
}

