package pe.izipay.common.core.domainobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pe.izipay.common.core.interfaces.IDomainObject;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class BaseDomainObject<T> implements IDomainObject<T> {

	protected T id;

	@Override
	public int hashCode() {
		return hashCodeId();
	}

	@Override
	public boolean equals(Object obj) {
		return equalsId(obj);
	}
	
	@NoArgsConstructor
	@SuperBuilder
	public static class IntegerDomainObject extends BaseDomainObject<Integer> { }
	
	@NoArgsConstructor
	@SuperBuilder
	public static class StringDomainObject extends BaseDomainObject<String> { }
}
