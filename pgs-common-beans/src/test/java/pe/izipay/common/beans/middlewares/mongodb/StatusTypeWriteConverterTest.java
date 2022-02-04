package pe.izipay.common.beans.middlewares.mongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.types.DataStatusType;

import java.util.Objects;

class StatusTypeWriteConverterTest {
    @Test
    void testConvert() {
        assertEquals(0, Objects.requireNonNull((new StatusTypeWriteConverter()).convert(DataStatusType.NONE)).intValue());
    }
}

