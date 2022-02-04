package pe.izipay.common.beans.repositories.mongodb;

import org.springframework.data.domain.Slice;
import pe.izipay.common.beans.CrudUtils;
import pe.izipay.common.core.exceptions.AppModuleException;
import pe.izipay.common.core.exceptions.AppRuntimeException;
import pe.izipay.common.core.exceptions.CommonModuleException;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.common.core.types.errors.CommonErrorType;

public abstract class BaseMongoDbRepository<T, K> extends CrudUtils {
	
	protected abstract FacadeMongoDbRepository<T, K> getMongoDbFacade();
	

	
	public T getModelById(K id) {
		var model = getMongoDbFacade().findById(id);
		if(model.isEmpty()) {
			throw EXCEPTION_RECORD_NOT_FOUND;
		}
		return model.get();
	}
	
	public T getFirst(Slice<T> slice) {
		if(slice.getNumberOfElements() > 0) {
			return slice.getContent().get(0);
		}
		throw EXCEPTION_RECORD_NOT_FOUND;
	}
}
