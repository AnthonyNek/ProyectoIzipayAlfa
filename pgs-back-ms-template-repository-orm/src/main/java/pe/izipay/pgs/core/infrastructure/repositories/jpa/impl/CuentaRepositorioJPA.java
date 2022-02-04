package pe.izipay.pgs.core.infrastructure.repositories.jpa.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.izipay.common.beans.CrudUtils;
import pe.izipay.common.beans.repositories.jpa.BaseJPARepository;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.pgs.core.application.repositories.CuentaRepositorio;
import pe.izipay.pgs.core.domain.types.CuentaVirtual;
import pe.izipay.pgs.core.domain.vo.CuentaIdentificadorVO;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.fachadas.CuentaFachadaJPA;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.mapeadores.CuentaMapeador;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.modelos.Cuenta;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Repository
public class CuentaRepositorioJPA extends BaseJPARepository<Cuenta, Long> implements CuentaRepositorio {

    private final CuentaFachadaJPA fachada;
    private final CuentaMapeador mapeador;
    /** Obtener titular por cuentaId
     * @param cuentaId
     * @param error
     */
    @Override
    public String obtenerTitularCuenta(String cuentaId, IReadOnlyError<?> error) {
        return CrudUtils.verifyRecord(fachada.findByCuenta_id(cuentaId), error).getTitular_id();
    }
    /** Obtener cuenta por cuentaId
     * @param cuentaId
     * @param error
     */
    @Override
    public CuentaVirtual obtenerCuenta(Integer cuentaId, IReadOnlyError<?> error) {
        return null;
    }

    /** Listar cuentas por Titular
     * @param titularId
     */
    @Override
    public List<CuentaIdentificadorVO> listarCuentasPorTitular(String titularId) {
        return fachada.listarPorTitular(titularId);
    }

}
