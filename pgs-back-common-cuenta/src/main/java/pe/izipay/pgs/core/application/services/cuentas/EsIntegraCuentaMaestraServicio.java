package pe.izipay.pgs.core.application.services.cuentas;

import com.fasterxml.jackson.core.JsonProcessingException;
import pe.izipay.pgs.core.domain.dto.cuentas.CalcularHashCuentaMaestraCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.EsIntegraCuentaMaestraCargaUtil;

public interface EsIntegraCuentaMaestraServicio {
    Boolean esIntegra(EsIntegraCuentaMaestraCargaUtil dto) throws JsonProcessingException;
    String calcularHash(CalcularHashCuentaMaestraCargaUtil dto) throws JsonProcessingException;
    void generarHash(CalcularHashCuentaMaestraCargaUtil dto) throws JsonProcessingException;
}
