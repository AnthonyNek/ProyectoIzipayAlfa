package pe.izipay.pgs.core.domain.dto.cuentas;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BloqueoCuentaCargaUtil {

    private String cuenta_id;
    private String condicion_operativa_id;
    private String usuario;
    private Date fecha_solictud;

}
