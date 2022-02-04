package pe.izipay.pgs.core.application.services.cuentas;

import pe.izipay.pgs.core.domain.dto.cuentas.AplicarSaldoCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.SaldoAfectado;
import pe.izipay.pgs.core.domain.types.Saldo;

import java.util.Collection;

public interface AplicarSaldosCuentaServicioGenerico {
    Collection<SaldoAfectado> aplicarSaldosCuentaComercio(AplicarSaldoCargaUtil dto);
    Collection<SaldoAfectado> aplicarSaldosCuentaMaestra(AplicarSaldoCargaUtil dto);
}
