package pe.izipay.common.core.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Component;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;

class AppErrorBuilderTest {
    @Test
    void testCreateErrorWithFormat() {
        BaseAppError<Object> baseAppError = new BaseAppError<>();
        baseAppError.setMessage("foo");
        BaseAppError<?> actualCreateErrorWithFormatResult = AppErrorBuilder.createErrorWithFormat(baseAppError, "Objects");
        assertNull(actualCreateErrorWithFormatResult.getCode());
        assertEquals("foo", actualCreateErrorWithFormatResult.getMessage());
        assertNull(actualCreateErrorWithFormatResult.getInput());
    }

    @Test
    void testCreateError() {
        BaseAppError<?> actualCreateErrorResult = AppErrorBuilder.createError(new BaseAppError<>(), "Input");
        assertNull(actualCreateErrorResult.getCode());
        assertNull(actualCreateErrorResult.getMessage());
        assertEquals("Input", actualCreateErrorResult.getInput());
    }

    @Test
    void testConstructor() {
        ArrayList<IReadOnlyError<?>> iReadOnlyErrorList = new ArrayList<>();
        assertSame(iReadOnlyErrorList, (new AppErrorBuilder(iReadOnlyErrorList)).getErrors());
    }

    @Test
    void testConstructor2() {
        assertTrue((new AppErrorBuilder()).getErrors().isEmpty());
    }

    @Test
    void testConstructor3() {
        assertTrue((new AppErrorBuilder(3)).getErrors().isEmpty());
    }

    @Test
    void testConstructor4() {
        assertTrue((new AppErrorBuilder(3, true)).getErrors().isEmpty());
    }

    @Test
    void testConstructor5() {
        assertTrue((new AppErrorBuilder(3, false)).getErrors().isEmpty());
    }

    @Test
    void testHasErrors() {
        assertFalse((new AppErrorBuilder()).hasErrors());
    }

    @Test
    void testHasErrors2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        appErrorBuilder.addMask(new BaseAppError<>());
        assertTrue(appErrorBuilder.hasErrors());
    }

    @Test
    void testClear() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        appErrorBuilder.clear();
        assertTrue(appErrorBuilder.getErrors().isEmpty());
    }

    @Test
    void testBuild() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        appErrorBuilder.addMask(new BaseAppError<>());
        assertThrows(AppModuleException.class, appErrorBuilder::build);
    }

    @Test
    void testBreakProcess() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.breakProcess());
    }

    @Test
    void testBreakProcess2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        appErrorBuilder.addMask(new BaseAppError<>());
        assertThrows(AppModuleException.class, appErrorBuilder::breakProcess);
    }

    @Test
    void testSubBuild() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Runnable runnable = mock(Runnable.class);
        doNothing().when(runnable).run();
        assertSame(appErrorBuilder, appErrorBuilder.subBuild(true, runnable));
        verify(runnable).run();
    }

    @Test
    void testSubBuild2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Runnable runnable = mock(Runnable.class);
        doThrow(new AppModuleException(new BaseAppError<>())).when(runnable).run();
        assertThrows(AppModuleException.class, () -> appErrorBuilder.subBuild(true, runnable));
        verify(runnable).run();
    }

    @Test
    void testSubBuild3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Runnable runnable = mock(Runnable.class);
        doNothing().when(runnable).run();
        assertSame(appErrorBuilder, appErrorBuilder.subBuild(false, runnable));
    }

    @Test
    void testAddMask() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        appErrorBuilder.addMask(new BaseAppError<>());
        assertEquals(1, appErrorBuilder.getErrors().size());
    }

    @Test
    void testAdd() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.add(new BaseAppError<>()));
    }

    @Test
    void testAddIfTrue() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfTrue(true, new BaseAppError<>()));
    }

    @Test
    void testAddIfTrue2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfTrue(false, new BaseAppError<>()));
    }

    @Test
    void testAddIfFalse() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfFalse(true, new BaseAppError<>()));
    }

    @Test
    void testAddIfFalse2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfFalse(false, new BaseAppError<>()));
    }

    @Test
    void testAddIfNull() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfNull("Value", new BaseAppError<>()));
    }

    @Test
    void testAddIfNull2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfNull(null, new BaseAppError<>()));
    }

    @Test
    void testAddIfEmpty() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfEmpty("42", new BaseAppError<>()));
    }

    @Test
    void testAddIfEmpty2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfEmpty((String) null, new BaseAppError<>()));
    }

    @Test
    void testAddIfEmpty3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfEmpty("", new BaseAppError<>()));
    }

    @Test
    void testAddIfEmpty4() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        ArrayList<Object> collection = new ArrayList<>();
        assertSame(appErrorBuilder, appErrorBuilder.addIfEmpty(collection, new BaseAppError<>()));
    }

    @Test
    void testAddIfEmpty5() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();

        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertSame(appErrorBuilder, appErrorBuilder.addIfEmpty(objectList, new BaseAppError<>()));
    }

    @Test
    void testAddIfTextHasNotMinLength() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer min = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfTextHasNotMinLength("42", min, new BaseAppError<>()));
        assertSame(appErrorBuilder, appErrorBuilder.addIfTextHasNotMinLength("", min, new BaseAppError<>()));
        assertSame(appErrorBuilder, appErrorBuilder.addIfTextHasNotMinLength("42", min, new BaseAppError<>()));
    }

    @Test
    void testAddIfTextHasNotMinLength4() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfTextHasNotMinLength("42", null, new BaseAppError<>()));
    }

    @Test
    void testAddObjectIdIfNullOrNotPattern() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        BaseAppError<Object> errorNull = new BaseAppError<>();
        assertSame(appErrorBuilder, appErrorBuilder.addObjectIdIfNullOrNotPattern("42", errorNull, new BaseAppError<>()));
        assertSame(appErrorBuilder, appErrorBuilder.addObjectIdIfNullOrNotPattern("999999999999999999999999", errorNull, new BaseAppError<>()));
    }

    @Test
    void testAddObjectIdIfNullOrNotPattern2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        BaseAppError<Object> errorNull = new BaseAppError<>();
        assertSame(appErrorBuilder, appErrorBuilder.addObjectIdIfNullOrNotPattern(null, errorNull, new BaseAppError<>()));
    }

    @Test
    void testAddIfTextOverRange() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfTextOverRange("42", 1, 3, new BaseAppError<>()));
        assertSame(appErrorBuilder, appErrorBuilder.addIfTextOverRange("Value", 1, 3, new BaseAppError<>()));
        assertSame(appErrorBuilder, appErrorBuilder.addIfTextOverRange("", 1, 3, new BaseAppError<>()));
    }

    @Test
    void testAddIfTextNotPattern() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfTextNotPattern("42", "Pattern", new BaseAppError<>()));
    }

    @Test
    void testAddIfTextNotPattern2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfTextNotPattern("Pattern", "Pattern", new BaseAppError<>()));
    }

    @Test
    void testAddIfTextNotPattern3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfTextNotPattern("", "Pattern", new BaseAppError<>()));
    }

    @Test
    void testAddIfTextNotPattern4() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfTextNotPattern("42", "42", new BaseAppError<>()));
    }

    @Test
    void testAddIfBlank() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfBlank("42", new BaseAppError<>()));
        assertSame(appErrorBuilder, appErrorBuilder.addIfBlank(null, new BaseAppError<>()));
        assertSame(appErrorBuilder, appErrorBuilder.addIfBlank("", new BaseAppError<>()));
    }

    @Test
    void testAddIfInvalidIdentifier() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfInvalidIdentifier(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfInvalidIdentifier2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = 0;
        assertSame(appErrorBuilder, appErrorBuilder.addIfInvalidIdentifier(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfInvalidIdentifier3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfInvalidIdentifier(null, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThan() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value1 = 1;
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThan(value1, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThan2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value1 = 0;
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThan(value1, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThan3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThan(null, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfGreaterThanOrEquals() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value1 = 1;
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfGreaterThanOrEquals(value1, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfGreaterThanOrEquals2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value1 = 0;
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfGreaterThanOrEquals(value1, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfGreaterThanOrEquals3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfGreaterThanOrEquals(null, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanOrEquals() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value1 = 1;
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanOrEquals(value1, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanOrEquals2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanOrEquals(42, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanOrEquals3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanOrEquals(null, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfEqualsTo() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value1 = 1;
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfEqualsTo(value1, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfEqualsTo2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value1 = 0;
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfEqualsTo(value1, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfEqualsTo3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value2 = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfEqualsTo(null, value2, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanZero() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanZero(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanZero2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = -1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanZero(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanZero3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanZero((byte) 'A', new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanZero4() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanZero((byte) '￿', new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanZero5() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanZero(null, new BaseAppError<>()));
    }

    @Test
    void testAddIfEqualsToZero() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfEqualsToZero(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfEqualsToZero2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = 0;
        assertSame(appErrorBuilder, appErrorBuilder.addIfEqualsToZero(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfEqualsToZero3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfEqualsToZero((byte) 'A', new BaseAppError<>()));
    }

    @Test
    void testAddIfEqualsToZero4() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfEqualsToZero((byte) 0, new BaseAppError<>()));
    }

    @Test
    void testAddIfEqualsToZero5() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfEqualsToZero(null, new BaseAppError<>()));
    }

    @Test
    void testAddIfDistinctZero() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfDistinctZero(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfDistinctZero2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = 0;
        assertSame(appErrorBuilder, appErrorBuilder.addIfDistinctZero(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfDistinctZero3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfDistinctZero((byte) 'A', new BaseAppError<>()));
    }

    @Test
    void testAddIfDistinctZero4() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfDistinctZero((byte) 0, new BaseAppError<>()));
    }

    @Test
    void testAddIfDistinctZero5() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfDistinctZero(null, new BaseAppError<>()));
    }

    @Test
    void testAddIfGreaterThanOrEqualsZero() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfGreaterThanOrEqualsZero(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfGreaterThanOrEqualsZero2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = -1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfGreaterThanOrEqualsZero(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfGreaterThanOrEqualsZero3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfGreaterThanOrEqualsZero((byte) 'A', new BaseAppError<>()));
    }

    @Test
    void testAddIfGreaterThanOrEqualsZero4() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfGreaterThanOrEqualsZero((byte) '￿', new BaseAppError<>()));
    }

    @Test
    void testAddIfGreaterThanOrEqualsZero5() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfGreaterThanOrEqualsZero(null, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanOrEqualsZero() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = 1;
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanOrEqualsZero(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanOrEqualsZero2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        Integer value = 0;
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanOrEqualsZero(value, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanOrEqualsZero3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanOrEqualsZero((byte) 'A', new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanOrEqualsZero4() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanOrEqualsZero((byte) 0, new BaseAppError<>()));
    }

    @Test
    void testAddIfLessThanOrEqualsZero5() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfLessThanOrEqualsZero(null, new BaseAppError<>()));
    }

    @Test
    void testAddIfOutsite() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder,
                appErrorBuilder.addIfOutsite(Component.BaselineResizeBehavior.CONSTANT_ASCENT,
                        Component.BaselineResizeBehavior.CONSTANT_ASCENT, Component.BaselineResizeBehavior.CONSTANT_ASCENT,
                        new BaseAppError<>()));
    }

    @Test
    void testAddIfOutsite2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfOutsite(null, Component.BaselineResizeBehavior.CONSTANT_ASCENT,
                Component.BaselineResizeBehavior.CONSTANT_ASCENT, new BaseAppError<>()));
    }

    @Test
    void testAddIfOutsite3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder,
                appErrorBuilder.addIfOutsite(Component.BaselineResizeBehavior.CONSTANT_DESCENT,
                        Component.BaselineResizeBehavior.CONSTANT_ASCENT, Component.BaselineResizeBehavior.CONSTANT_ASCENT,
                        new BaseAppError<>()));
    }

    @Test
    void testAddIfOutsite4() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder,
                appErrorBuilder.addIfOutsite(Component.BaselineResizeBehavior.CONSTANT_DESCENT,
                        Component.BaselineResizeBehavior.CONSTANT_DESCENT, Component.BaselineResizeBehavior.CONSTANT_ASCENT,
                        new BaseAppError<>()));
    }

    @Test
    void testAddIfOutsite5() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        var baseAppError = new BaseAppError<>();
        assertThrows(NullPointerException.class, () ->
            appErrorBuilder.addIfOutsite(null, baseAppError)
        );
    }

    @Test
    void testAddIfBeyond() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfBeyond(Component.BaselineResizeBehavior.CONSTANT_ASCENT,
                Component.BaselineResizeBehavior.CONSTANT_ASCENT, new BaseAppError<>()));
    }

    @Test
    void testAddIfBeyond2() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder,
                appErrorBuilder.addIfBeyond(null, Component.BaselineResizeBehavior.CONSTANT_ASCENT, new BaseAppError<>()));
    }

    @Test
    void testAddIfBeyond3() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfBeyond(Component.BaselineResizeBehavior.CONSTANT_DESCENT,
                Component.BaselineResizeBehavior.CONSTANT_ASCENT, new BaseAppError<>()));
    }

    @Test
    void testAddIfBeyond4() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfBeyond(Component.BaselineResizeBehavior.CENTER_OFFSET,
                Component.BaselineResizeBehavior.CONSTANT_ASCENT, new BaseAppError<>()));
    }

    @Test
    void testAddIfGreaterThan() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfGreaterThan(null, null, new BaseAppError<>()));
    }

    @Test
    void testAddIfGreaterThanZero() {
        AppErrorBuilder appErrorBuilder = new AppErrorBuilder();
        assertSame(appErrorBuilder, appErrorBuilder.addIfGreaterThanZero(null, new BaseAppError<>()));
    }
}

