package pe.izipay.common.beans.repositories.mongodb;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import lombok.Getter;
import pe.izipay.common.beans.repositories.DbProperties;
import pe.izipay.common.core.helpers.CollectionHelper;
import pe.izipay.common.core.helpers.RegexHelper;
import pe.izipay.common.core.helpers.TextHelper;
import pe.izipay.common.core.interfaces.IRange;
import pe.izipay.common.core.types.DataStatusType;

@Getter
public class MongoDbQueryBuilderFacade {			
	
	private final BasicDBObjectBuilder query;		
	
	public MongoDbQueryBuilderFacade() {
		query = BasicDBObjectBuilder.start();
		
	}
	
	private static Object defaultConverter(String value) {
		return value;
	}	
	
	public MongoDbQueryBuilderFacade addNotDeleted() {
		query.append(DbProperties.STATUS, new BasicDBObject("$ne", DataStatusType.DELETED.byteValue()));
		return this;
	}		
	
	protected MongoDbQueryBuilderFacade add(String field, String value, Function<String, Object> converter) {
		if(!TextHelper.isNullOrEmpty(value)) {
    		query.append(field, converter.apply(value));
    	}
		return this;
	}
	
	public MongoDbQueryBuilderFacade addIfHasValue(String field, String value) {
		return add(field, value, MongoDbQueryBuilderFacade::defaultConverter);
	}
	
	public MongoDbQueryBuilderFacade addObjectId(String field, String value) {
		return add(field, value, ObjectId::new);
	}

	public MongoDbQueryBuilderFacade addContainsInsentive(String field, String value) {
		return add(field, value, RegexHelper::createPatternContainsIgnoreCase);
	}
	
	public MongoDbQueryBuilderFacade addAllContainsInsentive(String value, String... fields) {
		if(!TextHelper.isNullOrEmpty(value)) {
			var regex = RegexHelper.createPatternContainsIgnoreCase(value);
			var or = new BasicDBList();
			for (String field : fields) {
				or.add(new BasicDBObject(field, regex));
			}
			query.append("$or", or);
    	}		
		return this;
	}
	
	private MongoDbQueryBuilderFacade addAllMask(String field, Collection<ObjectId> value) {
		if(!value.isEmpty()) {
			query.push(field).append("$in", value);	
		}		
		return this;
	}
	
	public MongoDbQueryBuilderFacade addAll(String field, Set<ObjectId> value) {
		return addAllMask(field, CollectionHelper.emptyIfNull(value));
	}
	
	public <T> MongoDbQueryBuilderFacade addAllObjectId(String field, Collection<T> value, Function<T, ObjectId> converter) {		
		value = CollectionHelper.emptyIfNull(value);
		var set = new HashSet<ObjectId>(value.size());
		for (var item : value) {
			set.add(converter.apply(item));
		}
		return addAllMask(field, set);
	}
	
	public MongoDbQueryBuilderFacade addAllObjectId(String field, Collection<String> value) {		
		return addAllObjectId(field, value, ObjectId::new);
	}
	
	public MongoDbQueryBuilderFacade addRange(String field, IRange<Date> value) {
		if(value != null && value.from() != null && value.to() != null
				&& value.from().compareTo(value.to()) < 0) {
			
			var range = new BasicDBObject();
			range.put("$gte", value.from());
			range.put("$lte", value.to());			
			query.append(field, range);
		} 
		return this;
	}
	
	public boolean isEmpty() {
		return query.isEmpty();
	}
	
	public DBObject build() {
		return query.get();
	}
}