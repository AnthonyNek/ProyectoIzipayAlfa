package pe.izipay.common.core.domainobjects.ranges;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;

@Getter
@Setter
public class DatesRangePayload<T extends Comparable<T>> {

	@Valid
	@NotNull(message = FieldErrorMapper.FIELD_NOT_NULL)
	private Range<T> rango_fechas;
	
	public static class DefaultDatesRangePayload extends DatesRangePayload<Date> { }
}
