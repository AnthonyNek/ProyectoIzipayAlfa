package pe.izipay.common.beans.repositories.redis;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(value = AccessLevel.PROTECTED)
public class BaseRedisRepository<K, V> {
	
	protected final HashOperations<String, K, V> hashOperations;
	protected final String collection;
	
	@SuppressWarnings("unchecked")
	public BaseRedisRepository(String collection, RedisTemplate<K, V> redisTemplate) {		
		this.hashOperations = (HashOperations<String, K, V>) redisTemplate.opsForHash();
		this.collection = collection;		
	}
	
	public Map<K, V> getAll(){
		return hashOperations.entries(collection);
	}
	
	public V get(K key){
		return hashOperations.get(collection, key);
	}
	
	public void put(K key, V value) {
		hashOperations.put(collection, key, value);
	}
	
	public void putIfAbsent(K key, V value) {
		hashOperations.putIfAbsent(collection, key, value);
	}
	
	public void delete(K key) {
		hashOperations.delete(collection, key);
	}

}