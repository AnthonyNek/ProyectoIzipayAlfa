package pe.izipay.common.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.Builder;
import pe.izipay.common.core.FieldValidation;
import pe.izipay.common.core.exceptions.AppErrorBuilder;
import pe.izipay.common.core.exceptions.BaseAppError;
import pe.izipay.common.core.helpers.RegexHelper;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;

@Builder
public class DataValidationHandler {
	
	private final Validator validator;
	private final Function<String, IReadOnlyError<?>> castError;
	private final IReadOnlyError<?> errorUnknow;
	private final Map<String, IReadOnlyError<?>>[] features;
	private final Function<Class<?>, Short> getIndex;

	public DataValidationHandler(Validator validator, Function<String, IReadOnlyError<?>> castError,
								 IReadOnlyError<?> errorUnknow, Map<String, IReadOnlyError<?>>[] features,
								 Function<Class<?>, Short> getIndex) {
		this.validator = validator;
		this.castError = castError;
		this.errorUnknow = errorUnknow;
		this.features = features;
		this.getIndex = getIndex;
	}

	public Collection<IReadOnlyError<?>> catchErrors(ConstraintViolationException ex) {
		Collection<IReadOnlyError<?>> errors = new ArrayList<>(ex.getConstraintViolations().size());
		var inputs = RegexHelper.readPatterns(ex.getMessage(), (byte) 1,
				FieldValidation.PATTERN_FIELD_ERROR_MAPPER_INPUT);
		int index = ex.getConstraintViolations().size();
		while (index > inputs.size()) {
			inputs.add("unknow");
		}
		index = 0;
		for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
			String message = constraintViolation.getMessage();
			Object code;
			try {
				var errorType = castError.apply(message);
				code = errorType.getCode();
				message = errorType.getMessage();
			} catch (Exception e) {
				code = RegexHelper.readPattern(message, (byte) 1, FieldValidation.PATTERN_FIELD_ERROR_MAPPER_CODE);
				try {
					message = castError.apply(message).getMessage();
				} catch (Exception e2) {
					code = errorUnknow.getCode();
				}
			}
			errors.add(new BaseAppError<>(code, message, inputs.get(index)));
			index++;
		}
		return errors;
	}

	public Collection<IReadOnlyError<?>> catchErrors(MethodArgumentNotValidException ex) {				
		var bindingResultErrors = ex.getBindingResult().getAllErrors();		
		Object target = ex.getTarget();
		Short index = target != null ? getIndex.apply(target.getClass()) : null;
		Function<String, IReadOnlyError<?>> search = index == null ? castError : features[index]::get;
		
		var errors = new LinkedList<IReadOnlyError<?>>();
		for (var error : bindingResultErrors) {
			String code = error.getDefaultMessage();
			String message = search.apply(code).getMessage();
			errors.add(new BaseAppError<>(code, message, ((FieldError) error).getField()));
		}
		return errors;
	}

	protected Collection<IReadOnlyError<?>> catchErrors(Object value) {
		var constraintViolations = validator.validate(value);
		Collection<IReadOnlyError<?>> list = new ArrayList<>(constraintViolations.size());
		IReadOnlyError<?> error;
		for (ConstraintViolation<?> item : constraintViolations) {
			var appError = new BaseAppError<>();
			appError.setInput(item.getPropertyPath().toString());
			try {
				error = castError.apply(item.getMessageTemplate());
				appError.setCode(error.getCode());
				appError.setMessage(error.getMessage());
			} catch (Exception ex) {
				appError.setMessage(errorUnknow.getMessage());
				appError.setCode(errorUnknow.getCode());
			}
			list.add(appError);
		}
		return list;
	}

	public AppErrorBuilder createAppErrorBuilder(Object value) {
		return new AppErrorBuilder(catchErrors(value));
	}

	public void throwErrors(Object value) {
		createAppErrorBuilder(value).build();
	}
}
