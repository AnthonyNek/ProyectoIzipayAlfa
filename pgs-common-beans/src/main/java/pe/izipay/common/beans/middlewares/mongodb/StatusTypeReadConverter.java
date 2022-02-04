package pe.izipay.common.beans.middlewares.mongodb;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import pe.izipay.common.core.types.DataStatusType;

@ReadingConverter
public class StatusTypeReadConverter implements Converter<Integer, DataStatusType> {
	
    @Override
    public DataStatusType convert(Integer value) {
        return DataStatusType.values()[value];
    }
}
