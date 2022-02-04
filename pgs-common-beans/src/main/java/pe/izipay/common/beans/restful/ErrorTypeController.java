package pe.izipay.common.beans.restful;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.izipay.common.core.exceptions.BaseCodeError;
import pe.izipay.common.core.exceptions.ErrorCodeDefinition;
import pe.izipay.common.core.interfaces.errors.IErrorType;
import pe.izipay.common.core.types.errors.CommonErrorType;
import pe.izipay.common.core.types.errors.FieldErrorType;
import pe.izipay.common.core.types.errors.GenericErrorType;
import pe.izipay.common.core.types.errors.RestErrorType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@RestController
@RequiredArgsConstructor
@Tag(name = RestCrudConstant.SWAGGER_ERROR_TYPES_TAG_NAME, description = RestCrudConstant.SWAGGER_ERROR_TYPES_TAG_DESCRIPTION)
@RequestMapping(		
		produces = MediaType.APPLICATION_JSON_VALUE		
)
public class ErrorTypeController extends ModuleController {

	private static final byte SHARED_ERROR_TYPE_GROUP_SIZE = 4;

	private final List<IErrorType<?>[]> groups;
	private final Supplier<IErrorType<?>[]> microserviceErrorsType;

	public ErrorTypeController(Supplier<IErrorType<?>[]> microserviceErrorsType) {
		this.microserviceErrorsType = microserviceErrorsType;
		groups = new ArrayList<>(SHARED_ERROR_TYPE_GROUP_SIZE + 1);
		groups.add(GenericErrorType.values());
		groups.add(RestErrorType.values());
		groups.add(CommonErrorType.values());
		groups.add(FieldErrorType.values());
	}

	@Operation(summary = RestCrudConstant.SWAGGER_ERROR_TYPES_DEFINITION_SUMMARY)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = RestCrudConstant.HTTP_STATUS_OK,
					description = RestCrudConstant.SWAGGER_ERROR_TYPES_DEFINITION_RESPONSE_DESCRIPTION)
			}
	)	
	@GetMapping(RestCrudConstant.PATH_ERROR_TYPE_DEFINITION)
    public ResponseEntity<List<ErrorCodeDefinition>> definition() {
		var errorTypeArray = microserviceErrorsType.get();
    	var list = new ArrayList<ErrorCodeDefinition>(errorTypeArray.length);
    	for (var item : errorTypeArray) { 
    		list.add(new ErrorCodeDefinition(item.id(), item.name(), item.getMessage()));
    	}
		return ResponseEntity.ok(list);
    }
	
	@Operation(summary = RestCrudConstant.SWAGGER_ERROR_TYPES_LIST_SUMMARY)
	@ApiResponses(value = {@ApiResponse(
			responseCode = RestCrudConstant.HTTP_STATUS_OK,
			description = RestCrudConstant.SWAGGER_ERROR_TYPES_LIST_SUMMARY)}
	)	
	@GetMapping(RestCrudConstant.PATH_ERROR_TYPE)
    public ResponseEntity<List<BaseCodeError.StringCodeError>> list() {
		var errorTypeArray = microserviceErrorsType.get();
    	var list = new ArrayList<BaseCodeError.StringCodeError>(errorTypeArray.length);
    	for (var item : errorTypeArray) { 
    		list.add(new BaseCodeError.StringCodeError(item.name(), item.getMessage()));
    	}
		return ResponseEntity.ok(list);
    }
}