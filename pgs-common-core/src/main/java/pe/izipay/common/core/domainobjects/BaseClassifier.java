package pe.izipay.common.core.domainobjects;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pe.izipay.common.core.interfaces.IClassifier;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class BaseClassifier<T> extends BaseDomainObject<T> implements IClassifier<T> {
		
	@JsonProperty("nombre")
	@ToString.Include
	protected String nombre;

	protected BaseClassifier(T id, String name) {
		super(id);
		this.nombre = name;
	}
			
	@Override
	public String toString() {
		return nombre;
	}

	@NoArgsConstructor	
	public static class ObjectLight extends BaseClassifier<Integer> {
	}

	@NoArgsConstructor	
	public static class ObjectLightShort extends BaseClassifier<Short> {
	}

	@NoArgsConstructor	
	public static class ObjectLightByte extends BaseClassifier<Byte> {
	}

	@NoArgsConstructor	
	public static class ObjectLightLong extends BaseClassifier<Long> {
	}
	
	@NoArgsConstructor	
	public static class ObjectLightString extends BaseClassifier<String> {
	}
	
	@NoArgsConstructor	
	public static class ObjectLightObjectId extends BaseClassifier<ObjectId> {
		
		@Override
		@JsonIgnore
		public ObjectId getId() {
			return super.getId();
		}
		
		@JsonProperty("id")
		public String getStringId() {
			return super.getId().toString();
		}
	}
}
