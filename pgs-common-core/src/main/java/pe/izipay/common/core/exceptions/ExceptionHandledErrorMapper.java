package pe.izipay.common.core.exceptions;

import lombok.Getter;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.common.core.types.errors.CommonErrorType;
import pe.izipay.common.core.types.errors.GenericErrorType;
import pe.izipay.common.core.types.errors.RestErrorType;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExceptionHandledErrorMapper {

	private static ExceptionHandledErrorMapper instance;

	protected final List<IReadOnlyError<?>> errorUnknown;
	protected final List<IReadOnlyError<?>> errorRecordNotFound;
	protected final List<IReadOnlyError<?>> errorRequestEndpointNotFound;
	protected final List<IReadOnlyError<?>> errorRequestEndpointNotImplemented;
	protected final List<IReadOnlyError<?>> errorRequestContentInvalid;
	protected final List<IReadOnlyError<?>> errorRequestMediaTypeNotSupported;	
	protected final List<IReadOnlyError<?>> errorMethodArgumentTypeMismatchException;
	
	protected ExceptionHandledErrorMapper() {
		errorUnknown = new ArrayList<>(1);
		errorRecordNotFound = new ArrayList<>(1);
		errorRequestEndpointNotFound = new ArrayList<>(1);
		errorRequestEndpointNotImplemented = new ArrayList<>(1);
		errorRequestContentInvalid = new ArrayList<>(1);
		errorRequestMediaTypeNotSupported = new ArrayList<>(1);
		errorMethodArgumentTypeMismatchException = new ArrayList<>(1);
	}

	public static ExceptionHandledErrorMapper getInstance() {
		if(instance == null) {
			instance = new ExceptionHandledErrorMapper();
			instance.errorUnknown.add(GenericErrorType.UNKNOWN);
			instance.errorRequestEndpointNotFound.add(RestErrorType.REQUEST_ENDPOINT_NOT_FOUND);
			instance.errorRequestEndpointNotImplemented.add(RestErrorType.REQUEST_ENDPOINT_NOT_IMPLEMENTED);
			instance.errorRequestContentInvalid.add(RestErrorType.REQUEST_CONTENT_INVALID);
			instance.errorRecordNotFound.add(CommonErrorType.RECORD_NOT_FOUND);
			instance.errorRequestMediaTypeNotSupported.add(RestErrorType.REQUEST_CONTENT_TYPE_NOT_SUPPORTED);
		}
		return instance;
	}
}