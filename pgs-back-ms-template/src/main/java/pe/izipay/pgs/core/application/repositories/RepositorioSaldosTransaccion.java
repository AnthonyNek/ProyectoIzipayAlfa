package pe.izipay.pgs.core.application.repositories;

import pe.izipay.pgs.core.domain.dto.cuentas.SaldoTransaccion;

import java.util.Collection;

public interface RepositorioSaldosTransaccion {

    Collection<SaldoTransaccion> obtenerSaldosTransacciones(int idTransaccion);

}
