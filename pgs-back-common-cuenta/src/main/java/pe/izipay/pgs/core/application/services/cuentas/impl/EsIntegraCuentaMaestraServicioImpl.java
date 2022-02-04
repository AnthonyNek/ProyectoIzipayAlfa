package pe.izipay.pgs.core.application.services.cuentas.impl;

import pe.izipay.pgs.core.application.services.cuentas.EsIntegraCuentaMaestraServicio;
import pe.izipay.pgs.core.domain.dto.cuentas.CalcularHashCuentaMaestraCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.EsIntegraCuentaMaestraCargaUtil;

public class EsIntegraCuentaMaestraServicioImpl implements EsIntegraCuentaMaestraServicio {
    @Override
    public Boolean esIntegra(EsIntegraCuentaMaestraCargaUtil dto) {
       String calculado = calcularHash(dto.getCalcularHashCargaUtil());
       if(dto.getHashActual().equals(calculado)){
           return true;
       } else
           return false;
    }

    @Override
    public String calcularHash(CalcularHashCuentaMaestraCargaUtil dto) {

        return "Hash calculado";
    }

    @Override
    public void generarHash(CalcularHashCuentaMaestraCargaUtil dto) {
        String calculado = calcularHash(dto);
        // repository.saveHash(dto.getId_cuenta);
    }
}
