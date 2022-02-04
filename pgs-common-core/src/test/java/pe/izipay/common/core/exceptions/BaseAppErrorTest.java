package pe.izipay.common.core.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class BaseAppErrorTest {
    @Test
    void testConstructor() {
        BaseAppError<Object> actualBaseAppError = new BaseAppError<>("Code", "Not all who wander are lost");

        Object expectedCode = actualBaseAppError.code;
        Object code = actualBaseAppError.getCode();
        assertSame(expectedCode, code);
        assertEquals("Code", code);
        assertEquals("Not all who wander are lost", actualBaseAppError.getMessage());
        assertNull(actualBaseAppError.getInput());
    }

    @Test
    void testConstructor2() {
        BaseAppError<Object> actualBaseAppError = new BaseAppError<>("Code", "Not all who wander are lost", "Input");

        Object expectedCode = actualBaseAppError.code;
        Object code = actualBaseAppError.getCode();
        assertSame(expectedCode, code);
        assertEquals("Code", code);
        assertEquals("Not all who wander are lost", actualBaseAppError.getMessage());
        assertEquals("Input", actualBaseAppError.getInput());
    }
}

