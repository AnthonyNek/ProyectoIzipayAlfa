package pe.izipay.common.core.domainobjects.cruds;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.FieldErrorMapper;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ActivablePayload extends DomainObjectPayload {

    @NotNull(message = FieldErrorMapper.FIELD_NOT_NULL)
    protected Boolean activo;
}
