
package pe.izipay.pgs.core.application.services.cuentas.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import pe.izipay.pgs.core.application.repositories.CuentaComercioRepositorio;
import pe.izipay.pgs.core.application.services.cuentas.EsIntegraCuentaComercioServicio;
import pe.izipay.pgs.core.domain.dto.cuentas.CalcularHashCuentaComercioCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.EsIntegraCuentaComercioCargaUtil;

@RequiredArgsConstructor
public class EsIntegraCuentaComercioServicioImpl implements EsIntegraCuentaComercioServicio {

    private final CuentaComercioRepositorio cuentaRepositorio;

    @Override
    public Boolean esIntegra(EsIntegraCuentaComercioCargaUtil dto) throws JsonProcessingException {
        String calculado = calcularHash(dto.getCalcularHashCargaUtil());
        if(dto.getHashActual().equals(calculado)){
            return true;
        } else
            return false;
    }

    @Override
    public String calcularHash(CalcularHashCuentaComercioCargaUtil dto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObject = objectMapper.writeValueAsString(dto);
        String hash = DigestUtils.sha3_256Hex(jsonObject);
        System.out.println(hash);
        return hash;
    }

    @Override
    public void generarHash(CalcularHashCuentaComercioCargaUtil dto) throws JsonProcessingException {
        String hashCalculado = calcularHash(dto);
        cuentaRepositorio.guardarHash(dto.getIdCuenta(), hashCalculado);
    }
}
