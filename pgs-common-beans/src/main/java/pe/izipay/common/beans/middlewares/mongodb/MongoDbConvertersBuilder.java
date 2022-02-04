package pe.izipay.common.beans.middlewares.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MongoDbConvertersBuilder {    
    
    public static MongoCustomConversions build() {
    	final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        //converters.add(new ZonedDateTimeReadConverter());
        //converters.add(new ZonedDateTimeWriteConverter());
    	converters.add(new StatusTypeReadConverter());
    	converters.add(new StatusTypeWriteConverter());
        return new MongoCustomConversions(converters);
    }
}