package pe.izipay.common.core.domainobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.cruds.DescribableEditPayload;

class DescribableEditPayloadTest {
    @Test
    void testConstructor() {
        DescribableEditPayload actualDescribableEditPayload = new DescribableEditPayload();
        actualDescribableEditPayload.setDescripcion("Descripcion");
        assertEquals("Descripcion", actualDescribableEditPayload.getDescripcion());
    }
}

