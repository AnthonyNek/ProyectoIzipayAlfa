package pe.izipay.common.core.domainobjects.audit;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.types.DataOriginType;

@Getter
@Setter
public class OriginAuditData {
	private String ipOrigen;
	private String dispositivoOrigen;
	private DataOriginType tipoOrigen;
}
