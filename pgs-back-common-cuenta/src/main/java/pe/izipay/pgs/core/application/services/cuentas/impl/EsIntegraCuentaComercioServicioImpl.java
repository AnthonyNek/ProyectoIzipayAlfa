package pe.izipay.pgs.core.application.services.cuentas.impl;

import pe.izipay.pgs.core.application.services.cuentas.EsIntegraCuentaComercioServicio;
import pe.izipay.pgs.core.domain.dto.cuentas.CalcularHashCuentaComercioCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.EsIntegraCuentaComercioCargaUtil;

public class EsIntegraCuentaComercioServicioImpl implements EsIntegraCuentaComercioServicio {
    @Override
    public Boolean esIntegra(EsIntegraCuentaComercioCargaUtil dto) {
        String calculado = calcularHash(dto.getCalcularHashCargaUtil());
        if(dto.getHashActual().equals(calculado)){
            return true;
        } else
            return false;
    }

    @Override
    public String calcularHash(CalcularHashCuentaComercioCargaUtil dto) {

        return "Hash calculado";
    }

    @Override
    public void generarHash(CalcularHashCuentaComercioCargaUtil dto) {
        String calculado = calcularHash(dto);
        // repository.saveHash(dto.getId_cuenta);
    }
}
