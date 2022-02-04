package pe.izipay.pgs.core.application.services.cuentas;

import pe.izipay.pgs.core.domain.dto.cuentas.CalcularHashCuentaComercioCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.EsIntegraCuentaComercioCargaUtil;

public interface EsIntegraCuentaComercioServicio {
    public Boolean esIntegra(EsIntegraCuentaComercioCargaUtil dto);
    public String calcularHash(CalcularHashCuentaComercioCargaUtil dto);
    public void generarHash(CalcularHashCuentaComercioCargaUtil dto);
}
