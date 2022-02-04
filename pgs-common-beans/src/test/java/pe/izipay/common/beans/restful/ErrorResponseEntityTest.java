package pe.izipay.common.beans.restful;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import pe.izipay.common.core.responses.ErrorWrappedResponse;

class ErrorResponseEntityTest {
    @Test
    void testConstructor() {
        ErrorWrappedResponse errorWrappedResponse = new ErrorWrappedResponse();
        errorWrappedResponse.setErrors(new ArrayList<>());
        ErrorResponseEntity actualErrorResponseEntity = new ErrorResponseEntity(errorWrappedResponse, HttpStatus.CONTINUE);

        assertTrue(actualErrorResponseEntity.getHeaders().isEmpty());
        assertTrue(actualErrorResponseEntity.hasBody());
        assertEquals(HttpStatus.CONTINUE, actualErrorResponseEntity.getStatusCode());
    }
}

