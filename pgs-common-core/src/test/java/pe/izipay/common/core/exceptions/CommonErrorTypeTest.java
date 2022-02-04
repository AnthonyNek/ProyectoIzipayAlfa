package pe.izipay.common.core.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pe.izipay.common.core.types.errors.GenericErrorType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommonErrorTypeTest {
    @Test
    void testGetCode() {
        Assertions.assertEquals("NONE", GenericErrorType.NONE.getCode());
    }

    @Test
    void testValueOf() {
        GenericErrorType actualValueOfResult = GenericErrorType.valueOf("NONE");
        assertNull(actualValueOfResult.getInput());
        assertEquals("null.", actualValueOfResult.getMessage());
    }
}

