package pe.izipay.pgs.core.domain.dto.cuentas;

import lombok.Data;
import pe.izipay.pgs.core.domain.types.TipoSaldo;

@Data
public class SaldoTransaccion {
    private TipoSaldo tipoSaldo;
    private Rubro rubro;
    private TipoOperacion tipoOperacion;
}
