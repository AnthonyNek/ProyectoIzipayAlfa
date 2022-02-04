package pe.izipay.common.beans.restful;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pe.izipay.common.core.responses.ErrorWrappedResponse;

public class ErrorResponseEntity extends ResponseEntity<ErrorWrappedResponse> {

	public ErrorResponseEntity(ErrorWrappedResponse body, HttpStatus status) {
		super(body, status);
	}

}
