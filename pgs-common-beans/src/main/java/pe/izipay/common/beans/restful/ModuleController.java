package pe.izipay.common.beans.restful;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import pe.izipay.common.core.FieldValidation;
import pe.izipay.common.core.exceptions.AppModuleException;
import pe.izipay.common.core.exceptions.ExceptionHandledErrorMapper;
import pe.izipay.common.core.helpers.RegexHelper;
import pe.izipay.common.core.helpers.TextHelper;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.common.core.responses.ArrayWrappedResponse;
import pe.izipay.common.core.responses.CollectionWrappedResponse;
import pe.izipay.common.core.responses.WrappedResponse;
import pe.izipay.common.core.responses.ErrorWrappedResponse;
import pe.izipay.common.core.responses.ObjectWrappedResponse;
import pe.izipay.common.core.types.errors.FieldErrorType;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Map;

public abstract class ModuleController {

    private static final AppModuleException EXCEPTION_ID = new AppModuleException(FieldErrorType.FIELD_ID_INVALID, "id");

    protected ExceptionHandledErrorMapper getMapper() {
        return ExceptionHandledErrorMapper.getInstance();
    }

    protected String getPatternId() {
        return FieldValidation.PATTERN_OBJECT_ID;
    }

	private <T> ResponseEntity<WrappedResponse<T>> wrapDataResponse(T obj, HttpStatus httpStatus) {
        WrappedResponse<T> bodyResponse = new WrappedResponse<>();
        bodyResponse.setData(obj);
        bodyResponse.setRequested(ZonedDateTime.now());        
        return ResponseEntity
        		.status(httpStatus)
        		.body(bodyResponse);
    }
	
    protected <T> ResponseEntity<WrappedResponse<T>> wrapCreateResponse(T obj) {
        return wrapDataResponse(obj, HttpStatus.CREATED);
    }
    
    protected <T> ResponseEntity<WrappedResponse<T>> wrapSingleDataResponse(T obj) {        
        return wrapDataResponse(obj, HttpStatus.OK);
    }
    
    protected <T> ResponseEntity<WrappedResponse<T>> wrapSingleNotNullDataResponse(T obj) {        
        return wrapDataResponse(obj, obj != null? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    
    protected <T> ResponseEntity<CollectionWrappedResponse<T>> wrapCollectionDataResponse(Collection<T> collection, HttpStatus httpStatus) {
        CollectionWrappedResponse<T> bodyResponse = new CollectionWrappedResponse<>();
        bodyResponse.setData(collection);
        bodyResponse.setRequested(ZonedDateTime.now());
        return ResponseEntity
        		.status(httpStatus)
        		.body(bodyResponse);
    }
    
    protected <T> ResponseEntity<CollectionWrappedResponse<T>> wrapCollectionDataResponse(Collection<T> collection) {
        return wrapCollectionDataResponse(collection, HttpStatus.OK);
    }
    
    protected <T> ResponseEntity<ArrayWrappedResponse<T>> wrapArrayDataResponse(T[] collection,
                                                                                HttpStatus httpStatus) {
        ArrayWrappedResponse<T> bodyResponse = new ArrayWrappedResponse<>();
        bodyResponse.setData(collection);
        bodyResponse.setRequested(ZonedDateTime.now());
        return ResponseEntity
        		.status(httpStatus)
        		.body(bodyResponse);
    }
    
    protected <T> ResponseEntity<ArrayWrappedResponse<T>> wrapArrayDataResponse(T[] collection) {
    	return wrapArrayDataResponse(collection, HttpStatus.OK);
    }

    private NonDataResponseEntity wrapNonDataResponse(HttpStatus httpStatus) {
        var bodyResponse = new ObjectWrappedResponse();
        bodyResponse.setRequested(ZonedDateTime.now());                       
        return new NonDataResponseEntity(bodyResponse, httpStatus);
    }        

    protected ResponseEntity<?> wrapNonDataResponse(boolean ok) {    	
        if(ok) {
        	var bodyResponse = new ObjectWrappedResponse();
            bodyResponse.setRequested(ZonedDateTime.now());
        	return ResponseEntity
            		.status(HttpStatus.OK)
            		.body(bodyResponse);
        } else {
        	var bodyResponse = new ErrorWrappedResponse();
            bodyResponse.setErrors(getMapper().getErrorRecordNotFound());
            bodyResponse.setRequested(ZonedDateTime.now());	
        	return ResponseEntity
            		.status(HttpStatus.NOT_FOUND)
            		.body(bodyResponse);
        }
    }
    
    protected NonDataResponseEntity wrapNonDataResponse() {
    	return wrapNonDataResponse(HttpStatus.OK);
    }
    
    public ErrorResponseEntity wrapErrorResponse(Collection<IReadOnlyError<?>> errors, HttpStatus httpStatus) {
        var bodyResponse = new ErrorWrappedResponse();        
        bodyResponse.setErrors(errors);
        bodyResponse.setRequested(ZonedDateTime.now());
        return new ErrorResponseEntity(bodyResponse, httpStatus);
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> getClaims() {
        return (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    protected String validarId(String id, String input) {
        boolean ok = RegexHelper.match(id, getPatternId());
        if(!ok) {
            throw new AppModuleException(FieldErrorType.FIELD_ID_INVALID, input);
        }
        return id;
    }

    protected String validarId(String id) {
        boolean ok = RegexHelper.match(id, getPatternId());
        if(!ok) {
            throw EXCEPTION_ID;
        }
        return id;
    }

    protected String validarBlank(String valor, String input) {
        valor = TextHelper.trim(valor);
        if(valor.isEmpty()) {
            throw new AppModuleException(FieldErrorType.FIELD_TEXT_NOT_EMPTY, input);
        }
        return valor;
    }
}