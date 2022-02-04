package pe.izipay.common.beans.middlewares;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

class JsonConversionDataConfigurationTest {
    @Test
    void testLoad() {
        JsonConversionDataConfiguration jsonConversionDataConfiguration = new JsonConversionDataConfiguration();
        jsonConversionDataConfiguration.setTimeFormat("42");
        jsonConversionDataConfiguration.setDateFormat("2020-03-01");
        jsonConversionDataConfiguration.setDateTimeFormat("2020/03/01");
        Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = mock(Jackson2ObjectMapperBuilder.class);
        when(jackson2ObjectMapperBuilder.deserializers(any()))
                .thenReturn(Jackson2ObjectMapperBuilder.json());
        when(jackson2ObjectMapperBuilder.serializers(any()))
                .thenReturn(Jackson2ObjectMapperBuilder.json());
        when(jackson2ObjectMapperBuilder.simpleDateFormat(any())).thenReturn(Jackson2ObjectMapperBuilder.json());
        jsonConversionDataConfiguration.load(jackson2ObjectMapperBuilder);
        verify(jackson2ObjectMapperBuilder, atLeast(1))
                .deserializers(any());
        verify(jackson2ObjectMapperBuilder, atLeast(1))
                .serializers(any());
        verify(jackson2ObjectMapperBuilder).simpleDateFormat(any());
    }
}

