package pe.izipay.common.core.domainobjects.paginations;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;

@Getter
@Setter
public abstract class WrappedPaginationPayload {
	
	@Valid
	@NotNull(message = FieldErrorMapper.FIELD_PAGINATION_NOT_NULL)
	private PaginationPayload paginacion;
    	
}
