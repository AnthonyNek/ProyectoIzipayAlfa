package pe.izipay.common.core.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AppErrorFinderTest {

    private static AppErrorFinder<Integer> appErrorFinder;

    @BeforeAll
    static void init() {
        var messageMapper = new HashMap<Integer, String>();
        messageMapper.put(1, "BaseAppError");
        appErrorFinder = new AppErrorFinder<>(messageMapper) {
            @Override
            public BaseAppError<Integer> createAppError(Integer code, String message, String input) {
                return new BaseAppError<>(code, message, input);
            }
        };
    }

    @Test
    void testFindError1() {
        assertEquals("BaseAppError", appErrorFinder.findMessage(1));
    }

    @Test
    void testErrorAddition() {
        var baseAppError = 1;

        assertSame(appErrorFinder.add(baseAppError), appErrorFinder);
        Assertions.assertEquals(appErrorFinder.getErrors().iterator().next().getCode(), baseAppError);
        assertTrue(appErrorFinder.hasErrors());
        assertSame(appErrorFinder.addIfEmpty("", baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfFalse(false, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfNull(null, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfInvalidIdentifier(-1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfGreaterThan(2, 1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfLessThan(2, 1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfGreaterThanOrEquals(2, 1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfLessThanOrEquals(2, 1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfEqualsTo(2, 1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfGreaterThanOrEqualsZero(1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfGreaterThanZero(1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfLessThanZero(1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfLessThanOrEqualsZero(1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfGreaterThanOrEqualsZero(1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfEmpty(new ArrayList<>(), baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfEqualsToZero(1, baseAppError), appErrorFinder);
        assertSame(appErrorFinder.addIfDistinctZero(1, baseAppError), appErrorFinder);
    }

    @Test
    void testCleanInvalid() {
        var list = new ArrayList<Integer>();
        list.add(2);
        var newCollection = appErrorFinder.cleanInvalid(list, 12);
        assertThat(newCollection).isNotNull();

        newCollection = appErrorFinder.cleanInvalid(list, 13);
        assertThat(newCollection).isNotNull();
    }
}
