package pe.izipay.common.beans.repositories.mongodb;

import java.util.ArrayList;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import pe.izipay.common.beans.repositories.DbProperties;

@NoRepositoryBean
public interface FacadeMongoDbRepository<T, K> extends MongoRepository<T, K> {
    	
    @Query(MongoDbQueries.PARAM_0)
    <E> ArrayList<E> query(DBObject queryObject, Class<E> cls);        
    
    default <E> ArrayList<E> listInIds(String field, Set<ObjectId> in, Class<E> cls) {
    	var builder = BasicDBObjectBuilder.start();
        builder.append(field, new BasicDBObject(MongoDbCondition.IN, in));        
        return query(builder.get(), cls);
    }
        
    default <E> ArrayList<E> listInIds(Set<ObjectId> in, Class<E> cls) {           
        return listInIds(DbProperties.MONGO_ID, in, cls);
    }
        
    default <E> ArrayList<E> listInIdsWithStatus(String fieldId, Set<ObjectId> in, byte status, Class<E> cls) {
    	var builder = BasicDBObjectBuilder.start();
    	builder.append(fieldId, new BasicDBObject(MongoDbCondition.IN, in));
        builder.append(DbProperties.STATUS, status);        
        return query(builder.get(), cls);
    }
        
    default <E> ArrayList<E> listInIdsWithStatus(Set<ObjectId> in, byte status, Class<E> cls) {
    	return listInIdsWithStatus(DbProperties.MONGO_ID, in, status, cls);
    }        
}
