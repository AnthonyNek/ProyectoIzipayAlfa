package pe.izipay.common.core.exceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import pe.izipay.common.core.FieldValidation;
import pe.izipay.common.core.helpers.CollectionHelper;
import pe.izipay.common.core.helpers.NumberHelper;
import pe.izipay.common.core.helpers.TextHelper;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.common.core.interfaces.IRange;

public class AppErrorBuilder {

	private final Collection<IReadOnlyError<?>> errors;

	public static BaseAppError<?> createErrorWithFormat(IReadOnlyError<?> code, Object ...objects) {
		return new BaseAppError<>(code.getCode(), String.format(code.getMessage(), objects));
	}
	
	public static BaseAppError<?> createError(IReadOnlyError<?> code, String input) {
		return new BaseAppError<>(code.getCode(), code.getMessage(), input);
	}
	
	public AppErrorBuilder(Collection<IReadOnlyError<?>> errors) {
		this.errors = errors;
	}

	public AppErrorBuilder(int capacity, boolean withDuplicated) {
		this(withDuplicated ? new ArrayList<>(capacity)
				: new HashSet<>(capacity));
	}
	
	public AppErrorBuilder(int capacity) {
		this(capacity, true);
	}

	public AppErrorBuilder() {
		this(5);
	}

	public Collection<IReadOnlyError<?>> getErrors() {
		return errors;
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public void clear() {
		errors.clear();
	}		
	
	public void build() {
		if (this.hasErrors()) {
			throw new AppModuleException(this.errors);
		}		
	}
	
	public AppErrorBuilder breakProcess() {
		build();
		return this;
	}
	
	public AppErrorBuilder subBuild(boolean apply, Runnable callBack) {
		if(apply) {
			callBack.run();
		}
		return this;
	}

	protected void addMask(IReadOnlyError<?> error) {
		errors.add(error);
	}

	public AppErrorBuilder add(IReadOnlyError<?> error) {
		addMask(error);
		return this;
	}

	public AppErrorBuilder addIfTrue(boolean value, IReadOnlyError<?> error) {
		if (value)
			addMask(error);
		return this;
	}

	public AppErrorBuilder addIfFalse(boolean expression, IReadOnlyError<?> error) {
		return addIfTrue(!expression, error);
	}

	public AppErrorBuilder addIfNull(Object value, IReadOnlyError<?> error) {
		return addIfTrue(value == null, error);
	}

	public AppErrorBuilder addIfEmpty(String value, IReadOnlyError<?> error) {
		return addIfTrue(value == null || value.isEmpty(), error);
	}
	
	public AppErrorBuilder addIfBlank(String value, IReadOnlyError<?> error) {
		return addIfTrue(value == null || value.isBlank(), error);
	}

	public AppErrorBuilder addIfInvalidIdentifier(Number value, IReadOnlyError<?> error) {
		return addIfTrue(value != null && value.longValue() < 1, error);
	}

	public <N extends Number & Comparable<Number>> AppErrorBuilder addIfGreaterThan(
			N value1, N value2, IReadOnlyError<?> error) {
		return addIfTrue(NumberHelper.isGreaterThan(value1, value2), error);
	}

	public <N extends Number & Comparable<N>> AppErrorBuilder addIfLessThan(
			N value1, N value2, IReadOnlyError<?> error) {
		return addIfTrue(NumberHelper.isLessThan(value1, value2), error);
	}

	public <N extends Number & Comparable<N>> AppErrorBuilder addIfGreaterThanOrEquals(
			N value1, N value2, IReadOnlyError<?> error) {
		return addIfTrue(NumberHelper.isGreaterThanOrEquals(value1, value2), error);
	}

	public <N extends Number & Comparable<N>> AppErrorBuilder addIfLessThanOrEquals(
			N value1, N value2, IReadOnlyError<?> error) {
		return addIfTrue(NumberHelper.isLessThanOrEquals(value1, value2), error);
	}

	public <N extends Number & Comparable<N>> AppErrorBuilder addIfEqualsTo(
			N value1, N value2, IReadOnlyError<?> error) {
		return addIfTrue(NumberHelper.isEquals(value1, value2), error);
	}

	public <N extends Number & Comparable<Number>> AppErrorBuilder addIfGreaterThanZero(
			N value, IReadOnlyError<?> error) {
		return addIfTrue(NumberHelper.isGreaterThan(value, 0), error);
	}

	public <N extends Number & Comparable<?>> AppErrorBuilder addIfLessThanZero(
			N value, IReadOnlyError<?> error) {
		return addIfTrue(NumberHelper.isLessThan(value, 0), error);
	}

	public <N extends Number & Comparable<?>> AppErrorBuilder addIfEqualsToZero(
			N value, IReadOnlyError<?> error) {
		return addIfTrue(NumberHelper.isEquals(value, 0), error);
	}

	public <N extends Number & Comparable<?>> AppErrorBuilder addIfDistinctZero(
			N value, IReadOnlyError<?> error) {
		return addIfTrue(NumberHelper.isDistinct(value, 0), error);
	}

	public <N extends Number & Comparable<?>> AppErrorBuilder addIfGreaterThanOrEqualsZero(
			N value, IReadOnlyError<?> error) {
		return addIfTrue(NumberHelper.isGreaterThanOrEquals(value, 0), error);
	}

	public <N extends Number & Comparable<?>> AppErrorBuilder addIfLessThanOrEqualsZero(
			N value, IReadOnlyError<?> error) {
		return addIfTrue(NumberHelper.isLessThanOrEquals(value, 0), error);
	}

	private AppErrorBuilder addIfOutsiteMask(int value, int lowerLimit, int topLimit,
			IReadOnlyError<?> error) {
		return addIfTrue(lowerLimit < value || value > topLimit, error);
	}

	public <E extends Enum<E>> AppErrorBuilder addIfOutsite(E value,
			E lowerLimit, E topLimit, IReadOnlyError<?> error) {
		if (value == null) {
			return add(error);
		} else {
			return addIfOutsiteMask(value.ordinal(), lowerLimit.ordinal(), topLimit.ordinal(), error);
		}
	}

	// Toma en cuenta el indice inferior con valor "1" de cualquier enumerador.
	public <E extends Enum<E>> AppErrorBuilder addIfBeyond(E value, E topLimit,
			IReadOnlyError<?> error) {
		if (value == null) {
			return add(error);
		} else {
			return addIfOutsiteMask(value.ordinal(), 1, topLimit.ordinal(), error);
		}
	}

	public <E extends Enum<E> & IRange.IEnumRange<E>> AppErrorBuilder addIfOutsite(
			E value, IReadOnlyError<?> error) {
		return addIfOutsite(value, value.from(), value.to(), error);
	}

	public <T> AppErrorBuilder addIfEmpty(Collection<T> collection,
			IReadOnlyError<?> error) {
		return addIfTrue(CollectionHelper.isEmpty(collection), error);
	}
	
	public <N extends Number & Comparable<?>> AppErrorBuilder addIfTextHasNotMinLength(
			String value, N min, IReadOnlyError<?> error) {
		if(min != null) {
			var minVal = min.intValue();
			return addIfTrue(minVal > 0 && value.length() < minVal, error);	
		}
		return this;
	}	
	
	public AppErrorBuilder addObjectIdIfNullOrNotPattern(String value, IReadOnlyError<?> errorNull, IReadOnlyError<?> errorObjectId) {
		if(value == null) {
			addMask(errorNull);
		} else if(value.matches(FieldValidation.PATTERN_OBJECT_ID)) {
			addMask(errorObjectId);
		}
		return this;
	}
	
	public AppErrorBuilder addIfTextOverRange(String value, int min, int max, IReadOnlyError<?> error) {		
		return addIfTrue(value.length() < min || value.length() > max, error);
	}
	
	public AppErrorBuilder addIfTextNotPattern(String value, String pattern, IReadOnlyError<?> error) {
		if(TextHelper.isNullOrEmpty(value) || !value.matches(pattern)) {
			addMask(error);
		}
		return this;
	}
}
