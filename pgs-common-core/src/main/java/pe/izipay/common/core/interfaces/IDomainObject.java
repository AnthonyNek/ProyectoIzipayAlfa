package pe.izipay.common.core.interfaces;

import java.util.Objects;

public interface IDomainObject<T> {
	
	T getId();
	void setId(T id);

	default int hashCodeId() {
		return Objects.hash(getId());
	}
	
	default boolean equalsId(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof IDomainObject) {			
			return Objects.equals(getId(), ((IDomainObject<?>)obj).getId());	
		}
		return false;		
	}		
}
