package pe.izipay.common.beans.middlewares.mongodb;

import org.springframework.core.convert.converter.Converter;

import pe.izipay.common.core.types.DataStatusType;

public class StatusTypeWriteConverter implements Converter<DataStatusType, Integer> {
	
    @Override
    public Integer convert(DataStatusType value) {
        return value.ordinal();
    }
}
