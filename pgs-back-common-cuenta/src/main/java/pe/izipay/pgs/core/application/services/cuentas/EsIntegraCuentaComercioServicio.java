package pe.izipay.pgs.core.application.services.cuentas;

import com.fasterxml.jackson.core.JsonProcessingException;
import pe.izipay.pgs.core.domain.dto.cuentas.CalcularHashCuentaComercioCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.EsIntegraCuentaComercioCargaUtil;

public interface EsIntegraCuentaComercioServicio {
    public Boolean esIntegra(EsIntegraCuentaComercioCargaUtil dto) throws JsonProcessingException;
    public String calcularHash(CalcularHashCuentaComercioCargaUtil dto) throws JsonProcessingException;
    public void generarHash(CalcularHashCuentaComercioCargaUtil dto) throws JsonProcessingException;
}
