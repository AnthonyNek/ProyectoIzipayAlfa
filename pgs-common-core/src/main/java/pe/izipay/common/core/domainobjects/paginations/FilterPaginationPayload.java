package pe.izipay.common.core.domainobjects.paginations;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterPaginationPayload<T> extends WrappedPaginationPayload {
	
	@Valid
	@NotNull(message = FieldErrorMapper.FIELD_PAGINATION_FILTER_NOT_NULL)	
    private T filtros;
}
