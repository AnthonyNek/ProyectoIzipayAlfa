package pe.izipay.common.beans.repositories.jpa;

import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import pe.izipay.common.beans.CrudUtils;

public abstract class BaseJPARepository<T, K> extends CrudUtils {

	protected abstract CrudRepository<T, K> getFachada();
	
	public T getModelById(K id) {
		var model = getFachada().findById(id);
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
