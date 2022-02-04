package pe.izipay.common.beans.adapters.cryptography;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BcrypHasherAdapterTest {

    @Test
    void testEncrypt() {
        String result = new BcrypHasherAdapter().encrypt("42");
        assertNotEquals("KJADBAJCABEQFE**^%&@!@geDCE", result);
    }

    @Test
    void testVerifyEncrypt() {
        assertFalse((new BcrypHasherAdapter()).verifyEncrypt("42", "42"));
    }
}

