
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

    /** Valida si es integra o no
     * @param dto
     */
    @Override
    public Boolean esIntegra(EsIntegraCuentaComercioCargaUtil dto) throws JsonProcessingException {
        String calculado = calcularHash(dto.getCalcularHashCargaUtil());
        if(dto.getHashActual().equals(calculado)){
            return true;
        } else
            return false;
    }
    /** Calcula el hash de la cuenta comercio
     * @param dto
     */
    @Override
    public String calcularHash(CalcularHashCuentaComercioCargaUtil dto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObject = objectMapper.writeValueAsString(dto);
        String hash = DigestUtils.sha3_256Hex(jsonObject);
        System.out.println(hash);
        return hash;
    }
    /** Genera y guarda el hash
     * @param calcularHashCuentaComercioCargaUtilDto
     */
    @Override
    public void generarHash(CalcularHashCuentaComercioCargaUtil calcularHashCuentaComercioCargaUtilDto) throws JsonProcessingException {
        String hashCalculado = calcularHash(calcularHashCuentaComercioCargaUtilDto);
        cuentaRepositorio.guardarHash(calcularHashCuentaComercioCargaUtilDto.getIdCuenta(), hashCalculado);
    }
}
