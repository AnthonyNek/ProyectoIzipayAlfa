package pe.izipay.common.core.actions;

import java.util.List;

import pe.izipay.common.core.domainobjects.paginations.PaginationPayload;
import pe.izipay.common.core.domainobjects.paginations.PaginationResult;

public class PaginationBuilder<T> {

	private final PaginationResult<T> pagination = new PaginationResult<>();
	
	public boolean totalRegistros(int totalRegistros) {
		pagination.setTotal_registros(totalRegistros);
		return totalRegistros > 0;
	}
	
	public PaginationBuilder<T> paginationPayload(PaginationPayload paginationPayload) {
		pagination.setCantidad_registros(paginationPayload.getCantidad_registros());
		pagination.setNro_pagina(paginationPayload.getNro_pagina());
		return this;
	}	
	
	public void lista(List<T> lista) {
		pagination.setLista(lista);
	}
	
	public PaginationResult<T> build() {
		int totalPaginas = pagination.getTotal_registros() / pagination.getCantidad_registros();
        if (pagination.getTotal_registros() % pagination.getCantidad_registros() > 0) {
        	totalPaginas++;
        }
		pagination.setTotal_paginas(totalPaginas);
		if(totalPaginas == 0) {
			pagination.setNro_pagina(0);
		}
		return pagination;
	}	
}
