package pe.izipay.common.core.domainobjects;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.cruds.ActivablePayload;

class ActivablePayloadTest {
    @Test
    void testConstructor() {
        ActivablePayload actualActivablePayload = new ActivablePayload();
        actualActivablePayload.setActivo(true);
        assertTrue(actualActivablePayload.getActivo());
    }
}

