package pe.izipay.pgs.core.domain.dto.cuentas;

import lombok.AllArgsConstructor;
import lombok.Data;
import pe.izipay.pgs.core.domain.types.TipoSaldo;

@Data
@AllArgsConstructor
public class SaldoTransaccion {
    private TipoSaldo tipoSaldo;
    private Rubro rubro;
    private TipoOperacion tipoOperacion;
}
