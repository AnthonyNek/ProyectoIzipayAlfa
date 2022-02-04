package pe.izipay.common.beans.restful;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import pe.izipay.common.core.responses.ObjectWrappedResponse;

class NonDataResponseEntityTest {
    @Test
    void testConstructor() {
        NonDataResponseEntity actualNonDataResponseEntity = new NonDataResponseEntity(new ObjectWrappedResponse(),
                HttpStatus.CONTINUE);

        assertTrue(actualNonDataResponseEntity.getHeaders().isEmpty());
        assertTrue(actualNonDataResponseEntity.hasBody());
        assertEquals(HttpStatus.CONTINUE, actualNonDataResponseEntity.getStatusCode());
    }
}

