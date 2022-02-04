package pe.izipay.common.beans.restful;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pe.izipay.common.core.responses.ObjectWrappedResponse;

public class NonDataResponseEntity extends ResponseEntity<ObjectWrappedResponse> {

	public NonDataResponseEntity(ObjectWrappedResponse body, HttpStatus status) {
		super(body, status);
	}

}
