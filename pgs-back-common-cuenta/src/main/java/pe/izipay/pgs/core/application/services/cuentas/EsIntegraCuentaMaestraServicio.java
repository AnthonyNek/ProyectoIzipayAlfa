package pe.izipay.pgs.core.application.services.cuentas;

import com.fasterxml.jackson.core.JsonProcessingException;
import pe.izipay.pgs.core.domain.dto.cuentas.CalcularHashCuentaMaestraCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.EsIntegraCuentaMaestraCargaUtil;

public interface EsIntegraCuentaMaestraServicio {
    public Boolean esIntegra(EsIntegraCuentaMaestraCargaUtil dto) throws JsonProcessingException;
    public String calcularHash(CalcularHashCuentaMaestraCargaUtil dto) throws JsonProcessingException;
    public void generarHash(CalcularHashCuentaMaestraCargaUtil dto) throws JsonProcessingException;
}
