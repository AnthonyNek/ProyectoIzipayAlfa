package pe.izipay.common.core.exceptions;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppRuntimeExceptionTest {

    @Test
    void testConstructor() {
        Throwable throwable = new Throwable();
        AppRuntimeException actualAppRuntimeException = new AppRuntimeException("An error occurred", throwable);

        Throwable cause = actualAppRuntimeException.getCause();
        assertSame(throwable, cause);
        assertEquals("pe.izipay.common.core.exceptions.AppRuntimeException: An error occurred",
            actualAppRuntimeException.toString());
        assertEquals("An error occurred", actualAppRuntimeException.getLocalizedMessage());
        Throwable[] suppressed = actualAppRuntimeException.getSuppressed();
        assertEquals(0, suppressed.length);
        assertTrue(actualAppRuntimeException.hasChildException());
        assertEquals("An error occurred", actualAppRuntimeException.getMessage());
        assertEquals("An error occurred; \nnull; \n", actualAppRuntimeException.readAllMessages());
        assertNull(cause.getLocalizedMessage());
        assertSame(suppressed, cause.getSuppressed());
        assertEquals("java.lang.Throwable", cause.toString());
        assertNull(cause.getCause());
        assertNull(cause.getMessage());
        assertSame(cause, throwable);
    }

    @Test
    void testConstructor2() {
        Throwable throwable = new Throwable();
        AppRuntimeException actualAppRuntimeException = new AppRuntimeException("An error occurred", throwable, true, true);

        Throwable cause = actualAppRuntimeException.getCause();
        assertSame(throwable, cause);
        assertEquals("pe.izipay.common.core.exceptions.AppRuntimeException: An error occurred",
            actualAppRuntimeException.toString());
        assertEquals("An error occurred", actualAppRuntimeException.getLocalizedMessage());
        Throwable[] suppressed = actualAppRuntimeException.getSuppressed();
        assertEquals(0, suppressed.length);
        assertTrue(actualAppRuntimeException.hasChildException());
        assertEquals("An error occurred", actualAppRuntimeException.getMessage());
        //assertEquals("An error occurred;  *** null;  *** ", actualAppRuntimeException.readAllMessages());
        assertNull(cause.getLocalizedMessage());
        assertSame(suppressed, cause.getSuppressed());
        assertEquals("java.lang.Throwable", cause.toString());
        assertNull(cause.getCause());
        assertNull(cause.getMessage());
        assertSame(cause, throwable);
    }

    @Test
    void testSetSeparator() {
        assertDoesNotThrow(() -> {
            AppRuntimeException.setSeparator("Separator");
            AppRuntimeException.setSeparator("");
        });
    }

    @Test
    void testHasChildException() {
        assertFalse((new AppRuntimeException("An error occurred")).hasChildException());
        assertFalse(AppRuntimeException.hasChildException(new Throwable()));
        assertFalse(AppRuntimeException.hasChildException(new Throwable()));
        assertFalse((new AppRuntimeException("An error occurred")).hasChildException());
    }

    @Test
    void testHasChildException2() {
        AppRuntimeException appRuntimeException = new AppRuntimeException(new Throwable());
        appRuntimeException.addSuppressed(new Throwable());
        assertTrue(appRuntimeException.hasChildException());
    }

    @Test
    void testHasChildException3() {
        IOException ioException = new IOException(new Throwable());
        ioException.addSuppressed(new Throwable());
        assertTrue(AppRuntimeException.hasChildException(ioException));
    }

    @Test
    void testHasChildException4() {
        IOException ioException = new IOException(new Throwable());
        ioException.addSuppressed(new Throwable());
        assertTrue(AppRuntimeException.hasChildException(ioException));
    }

    @Test
    void testHasChildException5() {
        AppRuntimeException appRuntimeException = new AppRuntimeException(new Throwable());
        appRuntimeException.addSuppressed(new Throwable());
        assertTrue(appRuntimeException.hasChildException());
    }

    @Test
    void testReadAll() {
        StringBuilder builder = new StringBuilder("Str");

        Throwable throwable = new Throwable();
        throwable.addSuppressed(new Throwable());
        assertDoesNotThrow(() -> AppRuntimeException.readAll(builder, throwable));
    }

    @Test
    void testReadAll2() {
        StringBuilder builder = new StringBuilder("Str");
        assertDoesNotThrow(() -> AppRuntimeException.readAll(builder, new Throwable(), "Separator"));
    }

    @Test
    void testReadAll3() {
        StringBuilder builder = new StringBuilder("Str");

        Throwable throwable = new Throwable();
        throwable.addSuppressed(new Throwable());
        assertDoesNotThrow(() -> AppRuntimeException.readAll(builder, throwable, "Separator"));
    }

    @Test
    void testReadAllMessages2() {
        IOException ioException = new IOException(new Throwable());
        ioException.addSuppressed(new Throwable());
        assertEquals("java.lang.Throwable; Separatornull; Separator",
                AppRuntimeException.readAllMessages(ioException, "Separator"));
    }
}

