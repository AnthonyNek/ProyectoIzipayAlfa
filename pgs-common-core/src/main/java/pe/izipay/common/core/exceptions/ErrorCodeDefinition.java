package pe.izipay.common.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorCodeDefinition {
	
	private int codeValue;
	private String codeName;
	private String message;
	
}