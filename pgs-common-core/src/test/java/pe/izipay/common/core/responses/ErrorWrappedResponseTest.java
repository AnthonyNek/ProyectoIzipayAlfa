package pe.izipay.common.core.responses;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;

class ErrorWrappedResponseTest {
    @Test
    void testConstructor() {
        ErrorWrappedResponse actualErrorWrappedResponse = new ErrorWrappedResponse();
        ArrayList<IReadOnlyError<?>> iReadOnlyErrorList = new ArrayList<>();
        actualErrorWrappedResponse.setErrors(iReadOnlyErrorList);
        assertSame(iReadOnlyErrorList, actualErrorWrappedResponse.getErrors());
    }
}

