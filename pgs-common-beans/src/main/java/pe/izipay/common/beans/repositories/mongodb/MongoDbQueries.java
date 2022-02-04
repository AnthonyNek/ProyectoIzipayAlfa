package pe.izipay.common.beans.repositories.mongodb;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class MongoDbQueries {		
	
	public static final String BY_ID = "{ '_id': ?0 }";
	public static final String STATUS_ACTIVE = "{ 'estado': 1 }";
	public static final String STATUS_ALL = "{ 'estado': { $ne : 3 } }";
	public static final String STATUS_ACTIVE_CONTAINS_NAME = "{ 'estado':1, 'nombre': {$regex:'.*?0.*'} }";	
	public static final String STATUS_ACTIVE_ID = "{ '_id': ?0, 'estado':1 }";
	public static final String STATUS_NOT_DELETED_ID = "{ '_id': ?0, 'estado' : { $ne : 3 } }";
	
	public static final String PARAM_LIMIT = "paramLimit";
	public static final String PARAM_SKIP = "paramSkip";
	public static final String PARAM_ID = "paramId";
	public static final String PARAM_NAME = "paramName";
	public static final String PARAM_0 = "?0";	
	
	public static final String AGGREGATION_STATUS_ALL = "{ $match: "+ STATUS_ALL +" }";
	public static final String AGGREGATION_LIMIT = "{ $limit: :#{#" + PARAM_LIMIT + "} }";
	public static final String AGGREGATION_EXIST = "{ $limit: 1 }";
	public static final String AGGREGATION_SKIP = "{ $skip: :#{#" + PARAM_SKIP + "} }";
	public static final String AGGREGATION_COUNT = "{ $count: 'count' }";		
}
