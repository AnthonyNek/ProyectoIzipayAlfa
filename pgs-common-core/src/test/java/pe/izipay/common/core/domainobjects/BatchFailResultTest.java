package pe.izipay.common.core.domainobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.batchs.BatchFailResult;

class BatchFailResultTest {
    @Test
    void testConstructor() {
        BatchFailResult<Object> actualBatchFailResult = new BatchFailResult<>();
        actualBatchFailResult.setMensaje("Mensaje");
        assertEquals("Mensaje", actualBatchFailResult.getMensaje());
    }
}

