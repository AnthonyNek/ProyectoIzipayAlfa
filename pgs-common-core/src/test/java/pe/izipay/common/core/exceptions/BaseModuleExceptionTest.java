package pe.izipay.common.core.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pe.izipay.common.core.interfaces.errors.IReadOnlyError;

class BaseModuleExceptionTest {

    @Test
    void testConstructor5() {
        Throwable cause = new Throwable();
        var listErrors = new ArrayList<IReadOnlyError<?>>(1);
        listErrors.add(new BaseAppError<>());
        assertEquals(1, (new AppModuleException(cause, listErrors)).getErrors().size());
    }
    @Test
    void testConstructor3() {
        Throwable cause = new Throwable();
        assertEquals(1, (new AppModuleException(cause, new BaseAppError<>())).getErrors().size());
    }

    @Test
    void testConstructor4() {
        assertEquals(1, (new AppModuleException(new BaseAppError<>())).getErrors().size());
    }

    @Test
    void testConvertToListError() {
    	var baseModuleException = new AppModuleException(new BaseAppError<>());
        assertEquals(1, baseModuleException.convertToListError(new BaseAppError<>()).size());
    }

    @Test
    void testCollectAllMessages() {
        assertEquals("null", (new AppModuleException(new BaseAppError<>())).collectAllMessages());
        assertEquals("", (new AppModuleException(new ArrayList<>())).collectAllMessages());
    }
}

