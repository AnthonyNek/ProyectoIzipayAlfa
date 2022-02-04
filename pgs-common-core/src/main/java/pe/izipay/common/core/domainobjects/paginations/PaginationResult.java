package pe.izipay.common.core.domainobjects.paginations;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginationResult<T> {
	
	private int total_registros;
	private int total_paginas;
	private int cantidad_registros;
    private int nro_pagina;
    private List<T> lista;
    
}
