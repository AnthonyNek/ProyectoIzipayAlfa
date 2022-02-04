package pe.izipay.common.core.interfaces;

public interface IDescribable<T> extends IClassifier<T> {

	String getDescripcion();
	void setDescripcion(String description);
}
