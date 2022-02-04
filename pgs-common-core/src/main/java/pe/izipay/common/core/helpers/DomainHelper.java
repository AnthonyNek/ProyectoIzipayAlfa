package pe.izipay.common.core.helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pe.izipay.common.core.interfaces.activables.IActivableWriter;
import pe.izipay.common.core.types.DataStatusType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DomainHelper {
	public static void setActivo(IActivableWriter activableEditable, Boolean activo) {
		activableEditable.setEstado((byte)(activo !=null && activo ? DataStatusType.ENABLED : DataStatusType.DISABLED).ordinal());
	}
}
