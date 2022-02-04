package pe.izipay.common.core.exceptions;

import java.util.Collection;
import java.util.Map;

import pe.izipay.common.core.helpers.CollectionHelper;
import pe.izipay.common.core.helpers.NumberHelper;

public abstract class AppErrorFinder<T> extends AppErrorBuilder {

	private Map<T, String> messagesMapper;

	protected AppErrorFinder(Map<T, String> messagesMapper, boolean withDuplicated) {
		super(5, withDuplicated);
		this.messagesMapper = messagesMapper;
	}

	protected AppErrorFinder(Map<T, String> messagesMapper) {
		this(messagesMapper, true);
		this.messagesMapper = messagesMapper;
	}

	public String findMessage(T errorType) {
		return messagesMapper.get(errorType);
	}

	public abstract BaseAppError<T> createAppError( T code, String message, String input);

	protected void addMask(T errorType) {
		super.addMask(createAppError(errorType, findMessage(errorType), null));
	}

	public AppErrorFinder<T> add(T errorType) {
		addMask(errorType);
		return this;
	}

	public AppErrorFinder<T> addIfTrue(boolean value, T errorType) {
		if (value)
			addMask(errorType);
		return this;
	}

	public AppErrorFinder<T> addIfFalse(boolean expression, T errorType) {
		return addIfTrue(!expression, errorType);
	}

	public AppErrorFinder<T> addIfNull(Object value, T errorType) {
		return addIfTrue(value == null, errorType);
	}

	public AppErrorFinder<T> addIfEmpty(String value, T errorType) {
		return addIfTrue(value == null || value.isEmpty(), errorType);
	}

	public AppErrorFinder<T> addIfInvalidIdentifier(Number value, T errorType) {
		return addIfTrue(value != null && value.longValue() < 1, errorType);
	}

	public <N extends Number & Comparable<?>> AppErrorFinder<T> addIfGreaterThan(N value1,
			N value2, T errorType) {
		return addIfTrue(NumberHelper.isGreaterThan(value1, value2), errorType);
	}

	public <N extends Number & Comparable<N>> AppErrorFinder<T> addIfLessThan(N value1,
			N value2, T errorType) {
		return addIfTrue(NumberHelper.isLessThan(value1, value2), errorType);
	}

	public <N extends Number & Comparable<N>> AppErrorFinder<T> addIfGreaterThanOrEquals(N value1,
			N value2, T errorType) {
		return addIfTrue(NumberHelper.isGreaterThanOrEquals(value1, value2), errorType);
	}

	public <N extends Number & Comparable<N>> AppErrorFinder<T> addIfLessThanOrEquals(N value1,
			N value2, T errorType) {
		return addIfTrue(NumberHelper.isLessThanOrEquals(value1, value2), errorType);
	}

	public <N extends Number & Comparable<N>> AppErrorFinder<T> addIfEqualsTo(N value1,
			N value2, T errorType) {
		return addIfTrue(NumberHelper.isEquals(value1, value2), errorType);
	}

	public <N extends Number & Comparable<N>> AppErrorFinder<T> addIfGreaterThanZero(N value,
			T errorType) {
		return addIfTrue(NumberHelper.isGreaterThan(value, 0), errorType);
	}

	public <N extends Number & Comparable<N>> AppErrorFinder<T> addIfLessThanZero(N value,
			T errorType) {
		return addIfTrue(NumberHelper.isLessThan(value, 0), errorType);
	}

	public <N extends Number & Comparable<N>> AppErrorFinder<T> addIfEqualsToZero(N value,
			T errorType) {
		return addIfTrue(NumberHelper.isEquals(value, 0), errorType);
	}

	public <N extends Number & Comparable<N>> AppErrorFinder<T> addIfDistinctZero(N value,
			T errorType) {
		return addIfTrue(NumberHelper.isDistinct(value, 0), errorType);
	}

	public <N extends Number & Comparable<N>> AppErrorFinder<T> addIfGreaterThanOrEqualsZero(
			N value, T errorType) {
		return addIfTrue(NumberHelper.isGreaterThanOrEquals(value, 0), errorType);
	}

	public <N extends Number & Comparable<N>> AppErrorFinder<T> addIfLessThanOrEqualsZero(N value,
			T errorType) {
		return addIfTrue(NumberHelper.isLessThanOrEquals(value, 0), errorType);
	}

	private AppErrorFinder<T> addIfOutsiteMask(int value, int lowerLimit, int topLimit, T error) {
		return addIfTrue(value < lowerLimit || value > topLimit, error);
	}

	public <E extends Enum<E>> AppErrorFinder<T> addIfOutsite(E value, E lowerLimit, E topLimit,
			T errorType) {
		if (value == null) {
			return add(errorType);
		} else {
			return addIfOutsiteMask(value.ordinal(), lowerLimit.ordinal(), topLimit.ordinal(), errorType);
		}
	}

	// Toma en cuenta el indice inferior con valor "1" de cualquier enumerador.
	public <E extends Enum<E>> AppErrorFinder<T> addIfBeyond(E value, E topLimit, T errorType) {
		if (value == null) {
			return add(errorType);
		} else {
			return addIfOutsiteMask(value.ordinal(), 1, topLimit.ordinal(), errorType);
		}
	}

	public <O> AppErrorFinder<T> addIfEmpty(Collection<O> collection, T errorType) {
		return addIfTrue(CollectionHelper.isEmpty(collection), errorType);
	}

	public <K extends Number> Collection<K> cleanInvalid(Collection<K> collection, T errorType) {
		if (CollectionHelper.isEmpty(collection)) {
			add(errorType);
		} else {
			collection = CollectionHelper.copyOnlyMoreThanZero(collection);
			if (collection.isEmpty()) {
				add(errorType);
			}
		}
		return collection;
	}

}