package pe.izipay.common.core.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pe.izipay.common.core.exceptions.CommonModuleException;
import pe.izipay.common.core.interfaces.IDomainObject;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CollectionHelper {
	public static boolean isEmpty(Collection<?> collecction) {
		return collecction == null || collecction.isEmpty();
	}	
	
	public static int size(Collection<?> collecction) {
		return collecction == null ? 0 : collecction.size();
	}

	public static <T> T firstOrDefaultObj(Collection<T> collecction, T obj) {
		for (var item : collecction) {
			if (Objects.equals(item, obj)) {
				return item;
			}
		}
		return null;
	}

	public static <T> T firstObj(Collection<T> collecction, T obj) {
		T value = firstOrDefaultObj(collecction, obj);
		if (value == null)
			throw new CommonModuleException("No se pudo encontrar el elemento deseado.");
		return value;
	}

	public static <K, T extends IDomainObject<K>> T firstOrDefault(Collection<T> collecction,
                                                                   K id) {
		for (var item : collecction) {
			if (Objects.equals(id, item.getId())) {
				return item;
			}
		}
		return null;
	}
	
	public static <F, T> T firstOrDefault(Collection<T> collecction, BiPredicate<T, F> predicate, F filter) {
		for (var item : collecction) {
			if (predicate.test(item, filter)) {
				return item;
			}
		}
		return null;
	}

	public static <K, T extends IDomainObject<K>> T first(Collection<T> collecction, K id) {
		T value = firstOrDefault(collecction, id);
		if (value == null)
			throw new CommonModuleException("No se pudo encontrar el elemento deseado.");
		return value;
	}

	public static <K extends Number> Collection<K> copyOnlyMoreThanZero(Collection<K> collection) {
		var list = new ArrayList<K>(collection.size());
		for (K item : collection) {
			if (item != null && item.longValue() > 0) {
				list.add(item);
			}
		}
		return list.size() < collection.size() ? list : collection;
	}
	
	public static <T> T firstOrDefault(Collection<T> collecction, Predicate<T> predicate) {		
		for (var item : collecction) {
			if (predicate.test(item)) {
				return item;
			}
		}
		return null;
	}

	public static <T> void forEach(Enumeration<T> enumerations, Consumer<? super T> action) {
		if(enumerations == null) return;

		Objects.requireNonNull(action);
		T item;
		while(enumerations.hasMoreElements()) {
			item = enumerations.nextElement();
			action.accept(item);
		}
	}
	
	public static <T> Collection<T> emptyIfNull(Collection<T> collection) {
		return collection == null ? new ArrayList<>(0) : collection;
	}
}