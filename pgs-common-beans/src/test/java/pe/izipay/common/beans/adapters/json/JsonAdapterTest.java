package pe.izipay.common.beans.adapters.json;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.BaseDomainObject;
import pe.izipay.common.core.exceptions.AppRuntimeException;
import pe.izipay.common.core.domainobjects.BaseDomainObject.StringDomainObject;

import java.util.HashMap;

class JsonAdapterTest {

    private static final JsonPort jsonAdapter = new JsonAdapter(new ObjectMapper());
    private static final String jsonExample = "{\"id\":\"test\"}";

    @Test
    void toJson() {
        var obj = new StringDomainObject();
        obj.setId("test");

        String jsonResult = jsonAdapter.toJson(obj);

        assertThat(jsonResult).isNotNull();
        assertThat(jsonResult.replace(" ","")).isEqualTo(jsonExample);
    }

    @Test
    void toObject() {
        var result = jsonAdapter.toObject(jsonExample, StringDomainObject.class);
        assertThat(result.getId()).isEqualTo("test");
        assertThrows(AppRuntimeException.class, () -> jsonAdapter.toObject("Test", StringDomainObject.class));
    }

    @Test
    void mapToObject() {
        var map = new HashMap<String, String>();
        map.put("id", "928");
        var result = jsonAdapter.mapToObject(map, BaseDomainObject.IntegerDomainObject.class);
        assertThat(result.getId()).isEqualTo(928);
    }
}