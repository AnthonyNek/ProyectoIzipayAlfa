package pe.izipay.pgs.core.application.services.cuentas;

import pe.izipay.pgs.core.domain.dto.cuentas.AplicarSaldoCargaUtil;

public interface AplicarSaldosCuentaServicioGenerico {
    public void aplicarSaldosCuentaComercio(AplicarSaldoCargaUtil dto);
    public void aplicarSaldosCuentaMaestra(AplicarSaldoCargaUtil dto);
}
