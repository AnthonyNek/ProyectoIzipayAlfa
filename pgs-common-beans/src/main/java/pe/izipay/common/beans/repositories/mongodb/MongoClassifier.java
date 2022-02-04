package pe.izipay.common.beans.repositories.mongodb;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.beans.repositories.DbProperties;

@Getter
@Setter
public abstract class MongoClassifier<T> {

	@Field(DbProperties.ID)
	private T id;
	private String nombre;
	
	public static class DefaultMongoClassifier extends MongoClassifier<String> { }
}