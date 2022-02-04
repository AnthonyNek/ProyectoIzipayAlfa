package pe.izipay.common.core.lambdas;

@FunctionalInterface
public interface ThreeConsumer<T, U, V> {

	void accept(T t, U u, V v);
}
