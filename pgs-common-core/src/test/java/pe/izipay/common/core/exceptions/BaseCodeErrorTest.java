package pe.izipay.common.core.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BaseCodeErrorTest {
    @Test
    void testIntegerCodeErrorConstructor() {
        BaseCodeError.IntegerCodeError actualIntegerCodeError = new BaseCodeError.IntegerCodeError(1,
                "Not all who wander are lost");

        assertEquals(1, actualIntegerCodeError.getCode().intValue());
        assertEquals("Not all who wander are lost", actualIntegerCodeError.getMessage());
    }

    @Test
    void testStringCodeErrorConstructor() {
        BaseCodeError.StringCodeError actualStringCodeError = new BaseCodeError.StringCodeError("Code",
                "Not all who wander are lost");

        assertEquals("Code", actualStringCodeError.getCode());
        assertEquals("Not all who wander are lost", actualStringCodeError.getMessage());
    }
}

