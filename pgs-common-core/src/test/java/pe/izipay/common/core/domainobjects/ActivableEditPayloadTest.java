package pe.izipay.common.core.domainobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.cruds.ActivableEditPayload;

class ActivableEditPayloadTest {
    @Test
    void testConstructor() {
        ActivableEditPayload actualActivableEditPayload = new ActivableEditPayload();
        actualActivableEditPayload.setId("42");
        assertEquals("42", actualActivableEditPayload.getId());
    }
}

