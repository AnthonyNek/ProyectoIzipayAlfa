package pe.izipay.common.beans.restful;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import pe.izipay.common.beans.DataValidationHandler;
import pe.izipay.common.core.exceptions.*;
import pe.izipay.common.core.interfaces.errors.IFeatureType;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.common.core.types.errors.CommonErrorType;
import pe.izipay.common.core.types.errors.GenericErrorType;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ModuleErrorHandlerTest {

    private ModuleErrorHandler moduleErrorHandler;

    static abstract class FakeIReadOnlyErrorIFeatureType implements IReadOnlyError<Object>, IFeatureType { }

    @BeforeEach
    void initModuleErrorHandler() {
        Map<String, IReadOnlyError<?>>[] features = IFeatureType.splitErrorsByFeatures(
                new IFeatureType[]{}, new FakeIReadOnlyErrorIFeatureType[]{}, mock(IFeatureType.class));

        Function<Class<?>, Short> function = (x) -> (short) 0;
        CustomValidatorBean validator = new CustomValidatorBean();
        Function<String, IReadOnlyError<?>> castError = (x -> GenericErrorType.DEFAULT);
        BaseAppError<Object> errorUnknow = new BaseAppError<>();
        DataValidationHandler dataValidationHandler = new DataValidationHandler(validator, castError, errorUnknow,
                features, function);
        this.moduleErrorHandler = new ModuleErrorHandler(dataValidationHandler) {
            @Override
            protected ExceptionHandledErrorMapper getMapper() {
                return ExceptionHandledErrorMapper.getInstance();
            }
        };
    }

    @Test
    void testHandleException() {

        HttpInputMessage mockHttpInputMessage = mock(HttpInputMessage.class);
        HttpHeaders mockHttpHeaders = mock(HttpHeaders.class);
        MethodParameter parameter = new MethodParameter(mock(MethodParameter.class));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, moduleErrorHandler.handleException(new AppRuntimeException()).getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, moduleErrorHandler.handleException(new AppModuleException(new BaseAppError<>())).getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, moduleErrorHandler.handleException(new HttpMessageNotReadableException("msg", mockHttpInputMessage)).getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, moduleErrorHandler.handleException(new CommonModuleException("exception")).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, moduleErrorHandler.handleException(new CommonModuleException("exception", CommonErrorType.RECORD_NOT_FOUND)).getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, moduleErrorHandler.handleException(new ConstraintViolationException(new HashSet<>())).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, moduleErrorHandler.handleException(new HttpRequestMethodNotSupportedException("msg")).getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, moduleErrorHandler.handleException(new HttpMediaTypeNotSupportedException("msg")).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, moduleErrorHandler.handleException(new NoHandlerFoundException("msg", "", mockHttpHeaders)).getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, moduleErrorHandler.handleException(new IllegalArgumentException()).getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, moduleErrorHandler.handleException(new MethodArgumentTypeMismatchException
            (1, String.class, "name", parameter, new IllegalArgumentException())).getStatusCode());
    }

    @Test
    void testWrapResponse() {
        assertEquals(HttpStatus.OK, moduleErrorHandler.wrapArrayDataResponse(null).getStatusCode());
        assertEquals(HttpStatus.OK, moduleErrorHandler.wrapNonDataResponse(true).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, moduleErrorHandler.wrapNonDataResponse(false).getStatusCode());
        assertEquals(HttpStatus.OK, moduleErrorHandler.wrapCollectionDataResponse(new ArrayList<>()).getStatusCode());
        assertEquals(HttpStatus.OK, moduleErrorHandler.wrapNonDataResponse().getStatusCode());
        assertEquals(HttpStatus.OK, moduleErrorHandler.wrapSingleDataResponse(null).getStatusCode());
        assertEquals(HttpStatus.OK, moduleErrorHandler.wrapSingleNotNullDataResponse(12).getStatusCode());
        assertEquals(HttpStatus.CREATED, moduleErrorHandler.wrapCreateResponse(12).getStatusCode());
    }
}
