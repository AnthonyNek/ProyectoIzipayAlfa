package pe.izipay.common.beans;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.web.bind.MethodArgumentNotValidException;
import pe.izipay.common.core.exceptions.BaseAppError;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;

class DataValidationHandlerTest {
    @Test
    void testCatchErrors() {
        CustomValidatorBean customValidatorBean = mock(CustomValidatorBean.class);
        when(customValidatorBean.validate(any(), (Class[]) any())).thenReturn(new HashSet<>());
        Function<String, IReadOnlyError<?>> castError = (Function<String, IReadOnlyError<?>>) mock(Function.class);
        BaseAppError<Object> errorUnknow = new BaseAppError<>();
        assertTrue((new DataValidationHandler(customValidatorBean, castError, errorUnknow, new Map[]{new HashMap<>()},
                (Function<Class<?>, Short>) mock(Function.class))).catchErrors("Value").isEmpty());
        verify(customValidatorBean).validate(any(), (Class[]) any());
    }

    @Test
    void testCatchErrors2() {
        CustomValidatorBean validator = new CustomValidatorBean();
        Function<String, IReadOnlyError<?>> castError = (Function<String, IReadOnlyError<?>>) mock(Function.class);
        BaseAppError<Object> errorUnknow = new BaseAppError<>();
        DataValidationHandler dataValidationHandler = new DataValidationHandler(validator, castError, errorUnknow,
                new Map[]{new HashMap<>()}, (Function<Class<?>, Short>) mock(Function.class));
        assertTrue(dataValidationHandler.catchErrors(new ConstraintViolationException(new HashSet<>())).isEmpty());
    }

    @Test
    void testCatchErrors3() {
        Function<Class<?>, Short> function = (Function<Class<?>, Short>) mock(Function.class);
        when(function.apply(any())).thenReturn((short) 0);
        CustomValidatorBean validator = new CustomValidatorBean();
        Function<String, IReadOnlyError<?>> castError = (Function<String, IReadOnlyError<?>>) mock(Function.class);
        BaseAppError<Object> errorUnknow = new BaseAppError<>();
        DataValidationHandler dataValidationHandler = new DataValidationHandler(validator, castError, errorUnknow,
                new Map[]{new HashMap<>()}, function);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getTarget()).thenReturn("Target");
        when(bindingResult.getAllErrors()).thenReturn(new ArrayList<>());
        BindException bindingResult1 = new BindException(new BindException(new BindException(bindingResult)));
        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(
                new MethodParameter(new MethodParameter(new MethodParameter(mock(MethodParameter.class)))), bindingResult1);

        assertTrue(dataValidationHandler.catchErrors(methodArgumentNotValidException).isEmpty());
        verify(function).apply(any());
        verify(bindingResult).getAllErrors();
        verify(bindingResult).getTarget();
        assertNull(methodArgumentNotValidException.getSuppressedFields());
    }

    @Test
    void testCatchErrors4() {
        Function<Class<?>, Short> function = (Function<Class<?>, Short>) mock(Function.class);
        when(function.apply(any())).thenReturn(null);
        CustomValidatorBean validator = new CustomValidatorBean();
        Function<String, IReadOnlyError<?>> castError = (Function<String, IReadOnlyError<?>>) mock(Function.class);
        BaseAppError<Object> errorUnknow = new BaseAppError<>();
        DataValidationHandler dataValidationHandler = new DataValidationHandler(validator, castError, errorUnknow,
                new Map[]{new HashMap<>()}, function);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getTarget()).thenReturn("Target");
        when(bindingResult.getAllErrors()).thenReturn(new ArrayList<>());
        BindException bindingResult1 = new BindException(new BindException(new BindException(bindingResult)));
        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(
                new MethodParameter(new MethodParameter(new MethodParameter(mock(MethodParameter.class)))), bindingResult1);

        assertTrue(dataValidationHandler.catchErrors(methodArgumentNotValidException).isEmpty());
        verify(function).apply(any());
        verify(bindingResult).getAllErrors();
        verify(bindingResult).getTarget();
        assertNull(methodArgumentNotValidException.getSuppressedFields());
    }

    @Test
    void testCatchErrors5() {
        Function<Class<?>, Short> function = (Function<Class<?>, Short>) mock(Function.class);
        when(function.apply(any())).thenReturn((short) 1);
        CustomValidatorBean validator = new CustomValidatorBean();
        Function<String, IReadOnlyError<?>> castError = (Function<String, IReadOnlyError<?>>) mock(Function.class);
        BaseAppError<Object> errorUnknow = new BaseAppError<>();
        DataValidationHandler dataValidationHandler = new DataValidationHandler(validator, castError, errorUnknow,
                new Map[]{new HashMap<>()}, function);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getTarget()).thenReturn(null);
        when(bindingResult.getAllErrors()).thenReturn(new ArrayList<>());
        BindException bindingResult1 = new BindException(new BindException(new BindException(bindingResult)));
        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(
                new MethodParameter(new MethodParameter(new MethodParameter(mock(MethodParameter.class)))), bindingResult1);

        assertTrue(dataValidationHandler.catchErrors(methodArgumentNotValidException).isEmpty());
        verify(bindingResult).getAllErrors();
        verify(bindingResult).getTarget();
        assertNull(methodArgumentNotValidException.getSuppressedFields());
    }

    @Test
    void testCreateAppErrorBuilder() {
        CustomValidatorBean customValidatorBean = mock(CustomValidatorBean.class);
        when(customValidatorBean.validate(any(), (Class[]) any())).thenReturn(new HashSet<>());
        Function<String, IReadOnlyError<?>> castError = (Function<String, IReadOnlyError<?>>) mock(Function.class);
        BaseAppError<Object> errorUnknow = new BaseAppError<>();
        assertTrue((new DataValidationHandler(customValidatorBean, castError, errorUnknow, new Map[]{new HashMap<>()},
                (Function<Class<?>, Short>) mock(Function.class))).createAppErrorBuilder("Value").getErrors().isEmpty());
        verify(customValidatorBean).validate(any(), (Class[]) any());
    }

    @Test
    void testThrowErrors() {
        CustomValidatorBean customValidatorBean = mock(CustomValidatorBean.class);
        when(customValidatorBean.validate(any(), (Class[]) any())).thenReturn(new HashSet<>());
        Function<String, IReadOnlyError<?>> castError = (Function<String, IReadOnlyError<?>>) mock(Function.class);
        BaseAppError<Object> errorUnknow = new BaseAppError<>();
        (new DataValidationHandler(customValidatorBean, castError, errorUnknow, new Map[]{new HashMap<>()},
                (Function<Class<?>, Short>) mock(Function.class))).throwErrors("Value");
        verify(customValidatorBean).validate(any(), (Class[]) any());
    }
}

