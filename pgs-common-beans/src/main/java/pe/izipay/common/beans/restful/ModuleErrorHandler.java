package pe.izipay.common.beans.restful;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import pe.izipay.common.beans.DataValidationHandler;
import pe.izipay.common.core.exceptions.AppModuleException;
import pe.izipay.common.core.exceptions.AppRuntimeException;
import pe.izipay.common.core.exceptions.CommonModuleException;
import pe.izipay.common.core.types.errors.CommonErrorType;

import javax.validation.ConstraintViolationException;

@RequiredArgsConstructor
@Slf4j
public abstract class ModuleErrorHandler extends ModuleController {		
			
	protected final DataValidationHandler dataValidationHandler;
		
    public ErrorResponseEntity handleBaseModuleAppException(AppModuleException ex) {
        return wrapErrorResponse(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }        
    
    public ErrorResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {    	
        return wrapErrorResponse(getMapper().getErrorRequestContentInvalid(), HttpStatus.BAD_REQUEST);
    }
    
    public ErrorResponseEntity handleCommonModuleException(CommonModuleException ex) {
    	if(ex.getError() != null) {
    		var errorType = ex.getError();
    		if(CommonErrorType.RECORD_NOT_FOUND.getCode().equals((errorType.getCode().toString()))) {
    			return wrapErrorResponse(getMapper().getErrorRecordNotFound(), HttpStatus.NOT_FOUND);
    		}    		
    	}
    	return wrapErrorResponse(getMapper().getErrorUnknown(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    public ErrorResponseEntity handleNoHandlerFoundException(Throwable ex) {    	
        return wrapErrorResponse(getMapper().getErrorRequestEndpointNotFound(), HttpStatus.NOT_FOUND);
    }
    
    public ErrorResponseEntity handleHttpRequestMethodNotSupportedException(Throwable ex) {    	
        return wrapErrorResponse(getMapper().getErrorRequestEndpointNotImplemented(), HttpStatus.NOT_FOUND);
    }
	
    public ErrorResponseEntity handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return wrapErrorResponse(getMapper().getErrorRequestMediaTypeNotSupported(), HttpStatus.BAD_REQUEST);        
    }
    
    public ErrorResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {    	        
        return wrapErrorResponse(getMapper().getErrorMethodArgumentTypeMismatchException(), HttpStatus.BAD_REQUEST);        
    }
    
    public ErrorResponseEntity handleAppRuntimeException(AppRuntimeException ex) {
        log.error(ex.readAll());
        return wrapErrorResponse(getMapper().getErrorUnknown(), HttpStatus.INTERNAL_SERVER_ERROR);        
    }                    
    
    public ErrorResponseEntity handleException(Throwable ex) {
		if(ex instanceof AppModuleException)
			return handleBaseModuleAppException((AppModuleException)ex);
		else if(ex instanceof CommonModuleException) 
			return handleCommonModuleException((CommonModuleException)ex);
		else if(ex instanceof HttpMessageNotReadableException)
			return handleHttpMessageNotReadableException((HttpMessageNotReadableException)ex);
		else if(ex instanceof MethodArgumentNotValidException)
			return wrapErrorResponse(dataValidationHandler.catchErrors((MethodArgumentNotValidException)ex), HttpStatus.BAD_REQUEST);					
		else if(ex instanceof ConstraintViolationException)
			return wrapErrorResponse(dataValidationHandler.catchErrors((ConstraintViolationException)ex), HttpStatus.BAD_REQUEST);			
		else if(ex instanceof NoHandlerFoundException) 
			return handleNoHandlerFoundException(ex);
		else if(ex instanceof HttpRequestMethodNotSupportedException) 
			return handleHttpRequestMethodNotSupportedException(ex);				
		else if(ex instanceof AppRuntimeException) 
			return handleAppRuntimeException((AppRuntimeException)ex);						
		else if(ex instanceof HttpMessageNotWritableException)
			return handleException(ex);
		else if(ex instanceof HttpMediaTypeNotSupportedException)
			return handleHttpMediaTypeNotSupportedException((HttpMediaTypeNotSupportedException)ex);
		else if(ex instanceof MethodArgumentTypeMismatchException)
			return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException)ex);
		else {
			log.error(AppRuntimeException.readAll(ex));
	        return wrapErrorResponse(getMapper().getErrorUnknown(), HttpStatus.INTERNAL_SERVER_ERROR);        
		}
	}    
}