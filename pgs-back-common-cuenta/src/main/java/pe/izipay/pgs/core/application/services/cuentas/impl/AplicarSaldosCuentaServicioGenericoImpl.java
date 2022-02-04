package pe.izipay.pgs.core.application.services.cuentas.impl;

import lombok.RequiredArgsConstructor;
import pe.izipay.pgs.core.application.repositories.RepositorioSaldosTransaccion;
import pe.izipay.pgs.core.application.services.cuentas.AplicarSaldosCuentaServicioGenerico;
import pe.izipay.pgs.core.application.services.cuentas.ConsultarSaldosCuentaServicio;
import pe.izipay.pgs.core.domain.dto.cuentas.AplicarSaldoCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.SaldoAfectado;
import pe.izipay.pgs.core.domain.dto.cuentas.SaldoTransaccion;
import pe.izipay.pgs.core.domain.dto.cuentas.TipoOperacion;
import pe.izipay.pgs.core.domain.types.Saldo;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class AplicarSaldosCuentaServicioGenericoImpl implements AplicarSaldosCuentaServicioGenerico {

    private final RepositorioSaldosTransaccion repositorioSaldosTransaccion;

    private final ConsultarSaldosCuentaServicio consultarSaldosCC;

    @Override
    public Collection<SaldoAfectado> aplicarSaldosCuentaComercio(AplicarSaldoCargaUtil dto) {
        Collection<SaldoTransaccion> saldoTransacciones = repositorioSaldosTransaccion.obtenerSaldosTransacciones(dto.getTrxId());
        Collection<Saldo> saldos = consultarSaldosCC.consultarSaldosCuenta(Integer.parseInt(dto.getCuentaId()));
        Collection<SaldoAfectado> saldosAfectados = new ArrayList<>();
        for( SaldoTransaccion saldoTransaccion : saldoTransacciones){
            Saldo saldoActual = saldos.stream().filter(saldo -> saldo.getTipoSaldo() == saldoTransaccion.getTipoSaldo()).findFirst().get();
            saldosAfectados.add(new SaldoAfectado(
                    dto.getCuentaId(),
                    saldoTransaccion.getRubro(),
                    saldoTransaccion.getTipoSaldo(),
                    saldoActual.getMonto(),
                    getMontoFinal(
                            saldoTransaccion.getTipoOperacion(),
                            saldoActual.getMonto(),
                            dto.getMontoPorTipo(saldoTransaccion.getRubro())
                    ),
                    dto.getMontoPorTipo(saldoTransaccion.getRubro()),
                    saldoTransaccion.getTipoOperacion()
            ));
            saldoActual.setMonto(getMontoFinal(
                    saldoTransaccion.getTipoOperacion(),
                    saldoActual.getMonto(),
                    dto.getMontoPorTipo(saldoTransaccion.getRubro())
            ));
        }
        return saldosAfectados;
    }

    @Override
    public void aplicarSaldosCuentaMaestra(AplicarSaldoCargaUtil dto) {

    }

    private double getMontoFinal(TipoOperacion tipoOperacion, double montoInicial, double monto) {
        if(TipoOperacion.INCREMENTAR == tipoOperacion)
            return montoInicial + monto;
        else
            return montoInicial - monto;
    }

}
