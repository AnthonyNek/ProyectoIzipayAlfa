package pe.izipay.common.beans.repositories.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import pe.izipay.common.beans.repositories.redis.BaseRedisRepository;

class BaseRedisRepositoryTest {
    @Test
    void testConstructor() {
        BaseRedisRepository<Object, Object> actualBaseRedisRepository = new BaseRedisRepository<>("Collection",
                new RedisTemplate<>());

        assertEquals("Collection", actualBaseRedisRepository.getCollection());
        HashOperations<String, Object, Object> expectedHashOperations = actualBaseRedisRepository.hashOperations;
        assertSame(expectedHashOperations, actualBaseRedisRepository.getHashOperations());
    }
}

