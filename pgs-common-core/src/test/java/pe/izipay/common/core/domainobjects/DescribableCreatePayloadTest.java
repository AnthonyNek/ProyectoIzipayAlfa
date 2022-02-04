package pe.izipay.common.core.domainobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.cruds.DescribableCreatePayload;

class DescribableCreatePayloadTest {
    @Test
    void testConstructor() {
        DescribableCreatePayload actualDescribableCreatePayload = new DescribableCreatePayload();
        actualDescribableCreatePayload.setDescripcion("Descripcion");
        assertEquals("Descripcion", actualDescribableCreatePayload.getDescripcion());
    }
}

