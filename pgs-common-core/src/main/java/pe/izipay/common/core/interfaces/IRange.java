package pe.izipay.common.core.interfaces;

public interface IRange<T extends Comparable<T>> {

	T from();
	T to();
	
	interface IEnumRange<E extends Enum<E>> extends IRange<E> {
	}	
}
