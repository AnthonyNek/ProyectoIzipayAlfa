package pe.izipay.common.beans.adapters.jwt;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.bouncycastle.jce.provider.JCEECPublicKey;
import org.junit.jupiter.api.Test;

class RSAJwtAdapterTest {
    @Test
    void testSetPublicKey() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setPublicKey(mock(JCEECPublicKey.class));
        assertTrue(rsaJwtAdapter.parser instanceof io.jsonwebtoken.impl.DefaultJwtParser);
    }
}

