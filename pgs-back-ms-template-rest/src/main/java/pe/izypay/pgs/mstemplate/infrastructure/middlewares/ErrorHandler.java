package pe.izypay.pgs.mstemplate.infrastructure.middlewares;

import javax.validation.Validator;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import pe.izypay.pgs.mstemplate.domain.FeatureGroup;
import pe.izypay.pgs.mstemplate.domain.types.ErrorType;
import pe.izipay.common.beans.DataValidationHandler;
import pe.izipay.common.beans.restful.ErrorResponseEntity;
import pe.izipay.common.beans.restful.ModuleErrorHandler;
import pe.izipay.common.beans.restful.RestCrudConstant;
import pe.izipay.common.core.responses.ErrorWrappedResponse;
import pe.izipay.common.core.types.errors.FieldErrorType;

@RestControllerAdvice
public class ErrorHandler extends ModuleErrorHandler {
	
	public ErrorHandler(Validator validator) {
		super(DataValidationHandler.builder()
				.castError(ErrorType::valueOf)
				.errorUnknow(FieldErrorType.FIELD_UNKNOWN)
				.validator(validator)
				.features(ErrorType.getFEATURES())
				.getIndex(clsTarget -> {										
					var annotation = clsTarget.getAnnotation(FeatureGroup.class);					
					return annotation == null ? null : annotation.value().shortValue();
				})
				.build());
	}
		
	@Override
	@ExceptionHandler(Throwable.class)	
	@ApiResponses(
			value = {					
					@ApiResponse(
							responseCode = RestCrudConstant.HTTP_STATUS_BAD_REQUEST,
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE, 
									schema = @Schema(
											implementation = ErrorWrappedResponse.StringErrorResponse.class
									)
							)
					)
			}
	)
	public ErrorResponseEntity handleException(Throwable ex) {
		return super.handleException(ex);
	}	
}
