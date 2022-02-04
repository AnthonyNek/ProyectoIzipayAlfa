package pe.izipay.pgs.core.application.repositories;

import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.pgs.core.domain.types.CuentaVirtual;
import pe.izipay.pgs.core.domain.vo.CuentaIdentificadorVO;

import java.util.List;

public interface CuentaRepositorio {

    /** Obtiene el titular de cuenta
     * @param cuentaId Integer
     * @param error IReadOnlyError
     */
    String obtenerTitularCuenta(String cuentaId, IReadOnlyError<?> error);
    /** Obtiene la cuenta del titular
     * @param cuentaId Integer
     * @param error IReadOnlyError
     */
    CuentaVirtual obtenerCuenta(Integer cuentaId, IReadOnlyError<?> error);
    /** Muestra la lista de cuentas por titular
     * @param titularId String
     */
    List<CuentaIdentificadorVO> listarCuentasPorTitular(String titularId);

}
