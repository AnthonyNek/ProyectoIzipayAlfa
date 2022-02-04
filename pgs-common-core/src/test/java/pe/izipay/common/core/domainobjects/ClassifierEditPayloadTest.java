package pe.izipay.common.core.domainobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.cruds.ClassifierEditPayload;

class ClassifierEditPayloadTest {
    @Test
    void testConstructor() {
        ClassifierEditPayload actualClassifierEditPayload = new ClassifierEditPayload();
        actualClassifierEditPayload.setId("42");
        assertEquals("42", actualClassifierEditPayload.getId());
    }
}

