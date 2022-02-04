package pe.izipay.common.core.domainobjects.paginations;

import javax.validation.constraints.Min;
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
public class PaginationPayload {
	
	@NotNull(message = FieldErrorMapper.FIELD_PAGINATION_SIZE_NOT_NULL)
    @Min(value = 1, message = FieldErrorMapper.FIELD_PAGINATION_SIZE_MIN)
    private Integer cantidad_registros;
    
    @NotNull(message = FieldErrorMapper.FIELD_PAGINATION_INDEX_NOT_NULL)
    @Min(value = 1, message = FieldErrorMapper.FIELD_PAGINATION_INDEX_MIN)
    private Integer nro_pagina;
    
}
