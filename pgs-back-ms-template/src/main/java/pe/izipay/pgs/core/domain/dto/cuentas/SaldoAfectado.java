package pe.izipay.pgs.core.domain.dto.cuentas;

import lombok.AllArgsConstructor;
import lombok.Data;
import pe.izipay.pgs.core.domain.types.TipoSaldo;

@Data
@AllArgsConstructor
public class SaldoAfectado {
    private String cuentaId;
    private Rubro rubroId;
    private TipoSaldo tipoSaldo;
    private double saldoInicial;
    private double saldoFinal;
    private double montoAfectado;
    private TipoOperacion tipoOperacion;
}
