package pe.izipay.common.beans.repositories.redis;

public interface ICrudRedisRepository<I, K> {
		
	I createInstance();	
	I get(K id);
	void save(I model);	
	void delete(K id);
}
