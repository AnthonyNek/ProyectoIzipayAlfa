package pe.izipay.pgs.core.application.services.cuentas.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.izipay.pgs.core.application.repositories.CuentaRepositorio;
import pe.izipay.pgs.core.application.services.cuentas.ObtenerRelacionEntreCuentasServicio;
import pe.izipay.pgs.core.domain.types.PGSCoreErrorType;
import pe.izipay.pgs.core.domain.vo.CuentaIdentificadorVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ObtenerRelacionEntreCuentasServicioImpl implements ObtenerRelacionEntreCuentasServicio {

    private final CuentaRepositorio repositorio;

    @Override
    public List<CuentaIdentificadorVO> ejecutar(String cuentaId) {
        var titularId = repositorio.obtenerTitularCuenta(cuentaId, PGSCoreErrorType.CUENTAS_TITULAR_NO_ENCONTRADO);
        return repositorio.listarCuentasPorTitular(titularId);
    }
}
