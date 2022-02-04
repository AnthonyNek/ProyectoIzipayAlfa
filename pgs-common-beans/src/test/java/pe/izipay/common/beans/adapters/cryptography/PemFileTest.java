package pe.izipay.common.beans.adapters.cryptography;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.exceptions.AppRuntimeException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PemFileTest {

    @Test
    void testConstructor() {
        assertThrows(AppRuntimeException.class, () -> new PemFile("foo.txt"));
    }

}

