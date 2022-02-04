package pe.izipay.pgs.core.application.services.cuentas;


import pe.izipay.pgs.core.domain.types.Saldo;

import java.math.BigDecimal;
import java.util.List;

public interface ConsultarSaldosCuentaServicio {

    public List<Saldo> consultarSaldosCuenta(Integer idCuenta);
    public BigDecimal consultarSaldosCuenta(Integer cuenta, Integer codTipoSaldo);
}
