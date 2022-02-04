package pe.izipay.common.core.interfaces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.cruds.ActivableCreatePayload;

class IActivableWriterTest {
    @Test
    void testSetActivo() {
        ActivableCreatePayload activableCreatePayload = new ActivableCreatePayload();
        activableCreatePayload.setActivo(true);
        assertTrue(activableCreatePayload.getActivo());
        assertEquals((byte) 1, activableCreatePayload.getEstado());
    }

    @Test
    void testSetActivo2() {
        ActivableCreatePayload activableCreatePayload = new ActivableCreatePayload();
        activableCreatePayload.setActivo(false);
        assertFalse(activableCreatePayload.getActivo());
        assertEquals((byte) 2, activableCreatePayload.getEstado());
    }
}

