package pe.izipay.common.core.domainobjects.cruds;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WrappedValuePayload<T> {

	@NotNull(message = FieldErrorMapper.FIELD_NOT_NULL)
	protected T valor;

	@Setter
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class WrappedValueEmail {	

		@NotNull(message = FieldErrorMapper.FIELD_NOT_NULL)			
		@Email(message = FieldErrorMapper.FIELD_EMAIL_INVALID)		
		protected String valor;	
		
	}
}
