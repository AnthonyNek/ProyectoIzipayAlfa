package pe.izipay.common.beans;

import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import pe.izipay.common.core.exceptions.AppModuleException;
import pe.izipay.common.core.exceptions.AppRuntimeException;
import pe.izipay.common.core.exceptions.CommonModuleException;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.common.core.domainobjects.paginations.PaginationPayload;
import pe.izipay.common.core.domainobjects.paginations.PaginationResult;
import pe.izipay.common.core.domainobjects.paginations.TotalRecordsResult;
import pe.izipay.common.core.domainobjects.paginations.WrappedPaginationPayload;
import pe.izipay.common.core.types.errors.CommonErrorType;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CrudUtils {

	protected static final AppRuntimeException EXCEPTION_RECORD_NOT_FOUND = new CommonModuleException(CommonErrorType.RECORD_NOT_FOUND);

	public static <D> D verifyRecord(D data) {
		if(data == null) {
			throw EXCEPTION_RECORD_NOT_FOUND;
		}
		return data;
	}

	public static <D> D verifyRecord(D data, IReadOnlyError<?> error) {
		if(data == null) {
			throw new AppModuleException(error);
		}
		return data;
	}

	public static <E> E verifyAmbiguity(List<E> list, IReadOnlyError<?> errorType) {
		if(list.isEmpty()) 
			return null; 			
		else if (list.size() > 1) 
			throw new AppModuleException(errorType);
		else
			return list.get(0);
	}
	
	public static PageRequest createPageable(PaginationPayload payload) {
		return PageRequest.of(payload.getNro_pagina() - 1, payload.getCantidad_registros());
	}
	
	public static PageRequest createPageableFirst() {
		return PageRequest.of(0, 1);
	}
	
	public static PageRequest createPageable(WrappedPaginationPayload wrapper) {
		return createPageable(wrapper.getPaginacion());
	}		
	
	public static <D> PaginationResult<D> createPaginationResult(Page<D> page) {
		return new PaginationResult<>(
				page.getNumberOfElements(),
				page.getTotalPages(),
				page.getSize(),
				page.getNumber() + 1,
				page.getContent());
	}
	
	public static long readTotalRecordsResult(TotalRecordsResult totalRecordsResul) {
		return totalRecordsResul == null ? 0 : totalRecordsResul.getCount(); 
	}
	
	public static int readTotalRecordsResultInt(TotalRecordsResult totalRecordsResul) {
		return (int)readTotalRecordsResult(totalRecordsResul); 
	}
	
	public static ObjectId toObjectIdString(String id) {
		return id == null ? null : new ObjectId(id);
	}
}
