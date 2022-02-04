package pe.izipay.pgs.core.application.services.cuentas.impl;

import pe.izipay.common.core.exceptions.AppRuntimeException;
import pe.izipay.common.core.exceptions.CommonModuleException;
import pe.izipay.common.core.types.errors.CommonErrorType;
import pe.izipay.pgs.core.application.repositories.CuentaFachadaJPA;
import pe.izipay.pgs.core.application.services.cuentas.ConsultarSaldosCuentaServicio;
import pe.izipay.pgs.core.domain.types.PGSCoreErrorType;
import pe.izipay.pgs.core.domain.types.Saldo;
import pe.izipay.pgs.core.domain.vo.CuentaTitularIdVO;

import java.math.BigDecimal;
import java.util.List;

public class ConsultarSaldosCuentaServicioImpl implements ConsultarSaldosCuentaServicio {

    private final CuentaFachadaJPA cuentaFachadaJPA;
    protected static final AppRuntimeException EXCEPTION_RECORD_NOT_FOUND = new CommonModuleException(CommonErrorType.RECORD_NOT_FOUND);

    /** Aplicar saldo para cuenta maestra
    * @param cuentaFachadaJPA
     */
    public ConsultarSaldosCuentaServicioImpl(CuentaFachadaJPA cuentaFachadaJPA) {
        this.cuentaFachadaJPA = cuentaFachadaJPA;
    }

    /** Aplicar saldo para cuenta maestra
    * @param idCuenta
     */
    @Override
    public List<Saldo> consultarSaldosCuenta(Integer idCuenta){
        if(idCuenta == null){
            throw EXCEPTION_RECORD_NOT_FOUND;
        } else{
            List<Saldo> saldos = cuentaFachadaJPA.listarSaldosPorCuenta(idCuenta);
            if(saldos.isEmpty()){
                throw EXCEPTION_RECORD_NOT_FOUND;
            } else
                return saldos;
        }

    }

    /** Consulta un tipo de saldo de una cuenta en especifica
     * @param idCuenta
     */
    @Override
    public BigDecimal consultarSaldosCuenta(Integer idCuenta, Integer codTipoSaldo) {
        BigDecimal saldo = cuentaFachadaJPA.obtenerSaldo(idCuenta, codTipoSaldo);
        CuentaTitularIdVO cuenta = cuentaFachadaJPA.findByCuenta_id(String.valueOf(idCuenta));
        if(idCuenta == null || codTipoSaldo==null){
            throw EXCEPTION_RECORD_NOT_FOUND;
        } else if(cuenta.equals(null)){
            throw EXCEPTION_RECORD_NOT_FOUND;
        } else
        return cuentaFachadaJPA.obtenerSaldo(idCuenta, codTipoSaldo);
    }
}
