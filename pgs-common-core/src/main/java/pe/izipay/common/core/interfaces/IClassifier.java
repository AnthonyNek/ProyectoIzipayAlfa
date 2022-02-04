package pe.izipay.common.core.interfaces;

public interface IClassifier<T> extends IDomainObject<T> {
	
	String getNombre();
	void setNombre(String nombre);
	String toString();
	
}
