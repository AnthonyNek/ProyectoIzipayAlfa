package pe.izipay.common.core.responses;

import java.time.LocalDate;
import java.time.chrono.ChronoZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
abstract class BaseWrappedResponse<T extends ChronoZonedDateTime<LocalDate>> {
	
	@JsonProperty("solicitado")
	protected T requested;

}