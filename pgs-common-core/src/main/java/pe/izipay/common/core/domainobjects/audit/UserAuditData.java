package pe.izipay.common.core.domainobjects.audit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserAuditData<T> extends OriginAuditData implements IUserAudit<T> {
	protected T usuarioId;
	protected String usuarioNombre;
}