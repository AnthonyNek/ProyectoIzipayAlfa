package pe.izipay.pgs.core.application.repositories;

import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.pgs.core.domain.types.CuentaVirtual;
import pe.izipay.pgs.core.domain.vo.CuentaIdentificadorVO;

import java.util.List;

public interface CuentaRepositorio {

    String obtenerTitularCuenta(String cuentaId, IReadOnlyError<?> error);
    CuentaVirtual obtenerCuenta(Integer cuentaId, IReadOnlyError<?> error);
    List<CuentaIdentificadorVO> listarCuentasPorTitular(String titularId);

}
