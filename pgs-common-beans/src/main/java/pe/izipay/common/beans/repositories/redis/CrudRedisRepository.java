package pe.izipay.common.beans.repositories.redis;

import org.springframework.data.repository.CrudRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrudRedisRepository<I, T extends I, K> 
	implements ICrudRedisRepository<I, K> {
	
	protected abstract CrudRepository<T, K> getFacade();
	
	@Override	
	public I get(K id) {		
		return getFacade().findById(id).orElse(null);
	}

	@Override	
	@SuppressWarnings("unchecked")
	public void save(I model) {
		getFacade().save((T)model);
	}

	@Override
	public void delete(K id) {
		getFacade().deleteById(id);
	}
}
