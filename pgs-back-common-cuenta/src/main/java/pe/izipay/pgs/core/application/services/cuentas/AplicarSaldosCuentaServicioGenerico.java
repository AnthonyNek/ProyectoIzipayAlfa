package pe.izipay.pgs.core.application.services.cuentas;

import pe.izipay.pgs.core.domain.dto.cuentas.AplicarSaldoCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.SaldoAfectado;

import java.util.Collection;

public interface AplicarSaldosCuentaServicioGenerico {
    public Collection<SaldoAfectado> aplicarSaldosCuentaComercio(AplicarSaldoCargaUtil dto);
    public void aplicarSaldosCuentaMaestra(AplicarSaldoCargaUtil dto);
}
