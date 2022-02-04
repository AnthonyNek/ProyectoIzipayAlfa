package pe.izipay.common.core.responses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class BaseWrappedResponseTest {
    @Test
    void testGetRequested() {
        assertNull((new ArrayWrappedResponse()).getRequested());
    }
}

