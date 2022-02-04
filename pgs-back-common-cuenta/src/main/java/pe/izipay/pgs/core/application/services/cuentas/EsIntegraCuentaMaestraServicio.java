package pe.izipay.pgs.core.application.services.cuentas;

import pe.izipay.pgs.core.domain.dto.cuentas.CalcularHashCuentaMaestraCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.EsIntegraCuentaMaestraCargaUtil;

public interface EsIntegraCuentaMaestraServicio {
    public Boolean esIntegra(EsIntegraCuentaMaestraCargaUtil dto);
    public String calcularHash(CalcularHashCuentaMaestraCargaUtil dto);
    public void generarHash(CalcularHashCuentaMaestraCargaUtil dto);
}
