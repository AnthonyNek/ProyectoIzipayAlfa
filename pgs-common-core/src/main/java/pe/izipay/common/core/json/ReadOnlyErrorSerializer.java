package pe.izipay.common.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;

import java.io.IOException;

public class ReadOnlyErrorSerializer extends StdSerializer<IReadOnlyError<?>> {
	
	private static final long serialVersionUID = 5704399045805817332L;

	public ReadOnlyErrorSerializer(Class<IReadOnlyError<?>> t) {
        super(t);
    }
    
    public ReadOnlyErrorSerializer() {
        this(null);
    }    	

	@Override
	public void serialize(IReadOnlyError<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("codigo", value.getCode().toString());
		gen.writeStringField("mensaje", value.getMessage());
		gen.writeStringField("entrada", value.getInput());
		gen.writeEndObject();
	}
	
}