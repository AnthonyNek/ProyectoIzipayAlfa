package pe.izipay.common.core.responses;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ArrayWrappedResponseTest {
    @Test
    void testConstructor() {
        ArrayWrappedResponse<Object> actualArrayWrappedResponse = new ArrayWrappedResponse<>();
        actualArrayWrappedResponse.setData(new Object[]{"Data"});
        Object[] expectedData = actualArrayWrappedResponse.data;
        assertSame(expectedData, actualArrayWrappedResponse.getData());
    }
}

