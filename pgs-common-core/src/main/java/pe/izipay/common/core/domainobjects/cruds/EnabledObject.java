package pe.izipay.common.core.domainobjects.cruds;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.izipay.common.core.domainobjects.BaseDomainObject;

@NoArgsConstructor
@Getter
@Setter
public class EnabledObject<T> extends BaseDomainObject<T> {

	@JsonProperty("activo")
	protected boolean enabled;

	public EnabledObject(T id, boolean enabled) {
		super(id);
		this.enabled = enabled;		
	}
}