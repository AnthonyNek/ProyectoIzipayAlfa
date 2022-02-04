package pe.izipay.common.core.domainobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pe.izipay.common.core.interfaces.IDescribable;

@NoArgsConstructor
@Getter
@Setter
@ToString(doNotUseGetters = true, includeFieldNames = false, onlyExplicitlyIncluded = true)
@SuperBuilder
public abstract class BaseDescribable<T> extends BaseClassifier<T> implements IDescribable<T> {

	@JsonProperty("descripcion")
	protected String descripcion;

	@NoArgsConstructor
	@SuperBuilder
	public static class DescribableString extends BaseDescribable<String> {
		
	}
	
}
