package pe.izipay.common.core.domainobjects.ranges;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;
import pe.izipay.common.core.interfaces.IRange;

@Getter
@Setter
public class Range<T extends Comparable<T>> implements IRange<T> {

	@NotNull(message = FieldErrorMapper.FIELD_NOT_NULL)
	private T desde;
	
	@NotNull(message = FieldErrorMapper.FIELD_NOT_NULL)
	private T hasta;
	
	public static class DateRange extends Range<Date> { }

	@JsonIgnore
	@Override
	public T from() {
		return desde;
	}

	@JsonIgnore
	@Override
	public T to() {
		return hasta;
	}
}
