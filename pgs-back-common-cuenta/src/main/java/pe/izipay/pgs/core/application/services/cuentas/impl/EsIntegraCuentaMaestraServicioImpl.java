package pe.izipay.pgs.core.application.services.cuentas.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import pe.izipay.pgs.core.application.repositories.CuentaMaestraRepositorio;
import pe.izipay.pgs.core.application.services.cuentas.EsIntegraCuentaMaestraServicio;
import pe.izipay.pgs.core.domain.dto.cuentas.CalcularHashCuentaMaestraCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.EsIntegraCuentaMaestraCargaUtil;

@RequiredArgsConstructor
public class EsIntegraCuentaMaestraServicioImpl implements EsIntegraCuentaMaestraServicio {

    private final CuentaMaestraRepositorio cuentaRepositorio;

    /** Valida si es integra o no
     * @param dto
     */
    @Override
    public Boolean esIntegra(EsIntegraCuentaMaestraCargaUtil dto) throws JsonProcessingException {
       String calculado = calcularHash(dto.getCalcularHashCargaUtil());
        return dto.getHashActual().equals(calculado);
    }

    /** Calcula el hash de la cuenta maestra
     * @param dto
     */
    @Override
    public String calcularHash(CalcularHashCuentaMaestraCargaUtil dto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObject = objectMapper.writeValueAsString(dto);
        String hash = DigestUtils.sha3_256Hex(jsonObject);
        return hash;
    }

    /** Genera y guarda el hash
     * @param dto
     */
    @Override
    public void generarHash(CalcularHashCuentaMaestraCargaUtil dto) throws JsonProcessingException {
        String hashCalculado = calcularHash(dto);
        cuentaRepositorio.guardarHash(dto.getIdCuenta(), hashCalculado);
    }
}
