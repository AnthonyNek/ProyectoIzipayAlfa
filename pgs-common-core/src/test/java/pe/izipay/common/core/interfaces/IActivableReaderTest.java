package pe.izipay.common.core.interfaces;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.cruds.ActiveClassifierStringResult;

class IActivableReaderTest {
    @Test
    void testIsActivo() {
        Assertions.assertFalse((new ActiveClassifierStringResult()).isActivo());
    }
}

