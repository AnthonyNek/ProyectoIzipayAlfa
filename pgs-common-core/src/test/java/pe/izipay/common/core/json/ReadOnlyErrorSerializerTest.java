package pe.izipay.common.core.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.exceptions.BaseAppError;

class ReadOnlyErrorSerializerTest {
    @Test
    void testSerialize() throws IOException {
        ReadOnlyErrorSerializer readOnlyErrorSerializer = new ReadOnlyErrorSerializer();
        BaseAppError<Object> value = new BaseAppError<>("Code", "Not all who wander are lost");

        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        doNothing().when(jsonGenerator).writeEndObject();
        doNothing().when(jsonGenerator).writeString((String) any());
        doNothing().when(jsonGenerator).writeFieldName((String) any());
        doNothing().when(jsonGenerator).writeStartObject();
        JsonGeneratorDelegate jsonGeneratorDelegate = new JsonGeneratorDelegate(
                new JsonGeneratorDelegate(new JsonGeneratorDelegate(new JsonGeneratorDelegate(jsonGenerator))));
        readOnlyErrorSerializer.serialize(value, jsonGeneratorDelegate, new DefaultSerializerProvider.Impl());
        verify(jsonGenerator).writeEndObject();
        verify(jsonGenerator, atLeast(1)).writeFieldName((String) any());
        verify(jsonGenerator).writeStartObject();
        verify(jsonGenerator, atLeast(1)).writeString((String) any());
        assertNull(((JsonGeneratorDelegate) jsonGeneratorDelegate.delegate()).delegate().getCharacterEscapes());
    }
}

