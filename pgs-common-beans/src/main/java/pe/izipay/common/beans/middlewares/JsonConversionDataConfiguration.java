package pe.izipay.common.beans.middlewares;

import java.time.format.DateTimeFormatter;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonConversionDataConfiguration {
	
	private String dateFormat;	
	private String timeFormat;		
	private String dateTimeFormat;		
		
	public Jackson2ObjectMapperBuilder load(Jackson2ObjectMapperBuilder builder) {
		builder.simpleDateFormat(dateTimeFormat);
        builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
        builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        builder.serializers(new ZonedDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        builder.serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)));            
        
        builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
        builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));            
        builder.deserializers(new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timeFormat)));
        return builder;
	}
}