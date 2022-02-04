package pe.izipay.pgs.core.application.services.cuentas.impl;

import pe.izipay.pgs.core.application.services.cuentas.AplicarSaldosCuentaServicioGenerico;
import pe.izipay.pgs.core.application.services.cuentas.ConsultarSaldosCuentaServicio;
import pe.izipay.pgs.core.domain.dto.cuentas.AplicarSaldoCargaUtil;
import pe.izipay.pgs.core.domain.types.Saldo;
import pe.izipay.pgs.core.domain.types.Transaccion;

import java.util.Collection;
import java.util.stream.Collectors;

public class AplicarSaldosCuentaServicioGenericoImpl implements AplicarSaldosCuentaServicioGenerico {

    private ConsultarSaldosCuentaServicio consultarSaldosCC;
    @Override
    public void aplicarSaldosCuentaComercio(AplicarSaldoCargaUtil dto) {
        Transaccion trx = consultarTrx(dto.getTrxId());
        Collection<Integer> tiposSaldosAfectados = consultarSaldosAfectados(trx);
        Collection<Saldo> saldos = consultarSaldosCC.consultarSaldosCuenta(dto.getCuentaId());

        //De toda la collection, filtra los tipos de saldos afectados
        Collection<Saldo> saldosFiltrados = saldos.stream().filter((saldo)->
            tiposSaldosAfectados.stream().anyMatch(tiposSaldo -> tiposSaldo.equals(saldo.getTipoSaldo()))
        ).collect(Collectors.toList());
        //Suponiendo que es una trx de venta (100)


    }

    @Override
    public void aplicarSaldosCuentaMaestra(AplicarSaldoCargaUtil dto) {

    }

    public Transaccion consultarTrx(Integer trx_id){
        try{
            //Consulta la transaccion por la bd
            //retorna Obj transaccion
            return null;
        }catch (Exception e){
            //mensaje de error especifico
            return null;
        }
    }

    public Collection<Integer> consultarSaldosAfectados(Transaccion trx){
        return null;
    }

}
