package pe.izipay.common.beans.adapters.cryptography;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecretKeyBcrypHasherAdapterTest {
    @Test
    void testConstructor() {
        assertDoesNotThrow(() ->
                new SecretKeyBcrypHasherAdapter(-1, "EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY")
        );
    }

    @Test
    void testConstructor2() {
        assertDoesNotThrow(() ->
                new SecretKeyBcrypHasherAdapter("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY")
        );
    }

    @Test
    void testEncrypt() {
        Assertions.assertDoesNotThrow(() ->
                (new SecretKeyBcrypHasherAdapter("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY")).encrypt("42")
        );
    }

    @Test
    void testVerifyEncrypt() {
        assertFalse(
                (new SecretKeyBcrypHasherAdapter("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY")).verifyEncrypt("42", "42"));
    }
}

