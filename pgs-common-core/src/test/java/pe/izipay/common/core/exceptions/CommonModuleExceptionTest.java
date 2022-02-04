package pe.izipay.common.core.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;

class CommonModuleExceptionTest {
    @Test
    void testConstructor() {
        CommonModuleException actualCommonModuleException = new CommonModuleException("An error occurred");
        assertNull(actualCommonModuleException.getCause());
        assertEquals("pe.izipay.common.core.exceptions.CommonModuleException: An error occurred",
                actualCommonModuleException.toString());
        /*assertEquals("An error occurred;  *** ", actualCommonModuleException.readAllMessages());*/
        assertFalse(actualCommonModuleException.hasChildException());
        assertEquals(0, actualCommonModuleException.getSuppressed().length);
        assertEquals("An error occurred", actualCommonModuleException.getMessage());
        assertEquals("An error occurred", actualCommonModuleException.getLocalizedMessage());
        assertNull(actualCommonModuleException.getError());
    }

    @Test
    void testConstructor2() {
        BaseAppError<Object> baseAppError = new BaseAppError<>();
        CommonModuleException actualCommonModuleException = new CommonModuleException("An error occurred", baseAppError);

        assertNull(actualCommonModuleException.getCause());
        assertEquals("pe.izipay.common.core.exceptions.CommonModuleException: An error occurred",
                actualCommonModuleException.toString());
        /*assertEquals("An error occurred;  *** ", actualCommonModuleException.readAllMessages());*/
        assertFalse(actualCommonModuleException.hasChildException());
        assertEquals(0, actualCommonModuleException.getSuppressed().length);
        assertEquals("An error occurred", actualCommonModuleException.getMessage());
        assertEquals("An error occurred", actualCommonModuleException.getLocalizedMessage());
        IReadOnlyError<?> iReadOnlyError = actualCommonModuleException.error;
        IReadOnlyError<?> error = actualCommonModuleException.getError();
        assertSame(iReadOnlyError, error);
        assertTrue(error instanceof BaseAppError);
        assertSame(iReadOnlyError, error);
        assertNull(error.getCode());
        assertNull(error.getMessage());
        assertNull(error.getInput());
        assertSame(error, baseAppError);
    }

    @Test
    void testConstructor3() {
        Throwable throwable = new Throwable();
        CommonModuleException actualCommonModuleException = new CommonModuleException(throwable);
        Throwable cause = actualCommonModuleException.getCause();
        assertSame(throwable, cause);
        assertEquals("pe.izipay.common.core.exceptions.CommonModuleException: java.lang.Throwable",
                actualCommonModuleException.toString());
        assertNull(actualCommonModuleException.getError());
        assertEquals("java.lang.Throwable", actualCommonModuleException.getMessage());
        /*assertEquals("java.lang.Throwable;  *** null;  *** ", actualCommonModuleException.readAllMessages());*/
        Throwable[] suppressed = actualCommonModuleException.getSuppressed();
        assertEquals(0, suppressed.length);
        assertEquals("java.lang.Throwable", actualCommonModuleException.getLocalizedMessage());
        assertTrue(actualCommonModuleException.hasChildException());
        assertNull(cause.getCause());
        assertNull(cause.getMessage());
        assertSame(suppressed, cause.getSuppressed());
        assertEquals("java.lang.Throwable", cause.toString());
        assertNull(cause.getLocalizedMessage());
        assertSame(cause, throwable);
    }

    @Test
    void testConstructor4() {
        Throwable throwable = new Throwable();
        BaseAppError<Object> baseAppError = new BaseAppError<>();
        CommonModuleException actualCommonModuleException = new CommonModuleException(throwable, baseAppError);

        Throwable cause = actualCommonModuleException.getCause();
        assertSame(throwable, cause);
        assertEquals("pe.izipay.common.core.exceptions.CommonModuleException: java.lang.Throwable",
                actualCommonModuleException.toString());
        IReadOnlyError<?> iReadOnlyError = actualCommonModuleException.error;
        IReadOnlyError<?> error = actualCommonModuleException.getError();
        assertSame(iReadOnlyError, error);
        assertTrue(error instanceof BaseAppError);
        assertEquals("java.lang.Throwable", actualCommonModuleException.getMessage());
        assertSame(iReadOnlyError, error);
        /*assertEquals("java.lang.Throwable;  *** null;  *** ", actualCommonModuleException.readAllMessages());*/
        Throwable[] suppressed = actualCommonModuleException.getSuppressed();
        assertEquals(0, suppressed.length);
        assertEquals("java.lang.Throwable", actualCommonModuleException.getLocalizedMessage());
        assertTrue(actualCommonModuleException.hasChildException());
        assertNull(cause.getCause());
        assertEquals("java.lang.Throwable", cause.toString());
        assertNull(error.getCode());
        assertNull(error.getInput());
        assertNull(cause.getMessage());
        assertSame(suppressed, cause.getSuppressed());
        assertNull(cause.getLocalizedMessage());
        assertNull(error.getMessage());
        assertSame(cause, throwable);
        assertSame(error, baseAppError);
    }

    @Test
    void testConstructor5() {
        BaseAppError<Object> baseAppError = new BaseAppError<>();
        CommonModuleException actualCommonModuleException = new CommonModuleException(baseAppError);
        assertNull(actualCommonModuleException.getCause());
        assertEquals("pe.izipay.common.core.exceptions.CommonModuleException", actualCommonModuleException.toString());
        /*assertEquals("null;  *** ", actualCommonModuleException.readAllMessages());*/
        assertFalse(actualCommonModuleException.hasChildException());
        assertEquals(0, actualCommonModuleException.getSuppressed().length);
        assertNull(actualCommonModuleException.getMessage());
        assertNull(actualCommonModuleException.getLocalizedMessage());
        IReadOnlyError<?> iReadOnlyError = actualCommonModuleException.error;
        IReadOnlyError<?> error = actualCommonModuleException.getError();
        assertSame(iReadOnlyError, error);
        assertTrue(error instanceof BaseAppError);
        assertSame(iReadOnlyError, error);
        assertNull(error.getCode());
        assertNull(error.getMessage());
        assertNull(error.getInput());
        assertSame(error, baseAppError);
    }
}

