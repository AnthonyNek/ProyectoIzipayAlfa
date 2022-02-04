package pe.izipay.pgs.core.infrastructure.repositories.jpa.fachadas;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.izipay.pgs.core.domain.dto.cuentas.ConsultarSaldosCargaUtil;
import pe.izipay.pgs.core.domain.vo.CuentaIdentificadorVO;
import pe.izipay.pgs.core.domain.vo.CuentaTitularIdVO;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.modelos.Cuenta;

import java.math.BigDecimal;
import java.util.LinkedList;

public interface CuentaFachadaJPA extends CrudRepository<Cuenta, Long> {

    /** Mostrar el titular de la cuenta por id
     * @param cuentaId
     */
    @Query("SELECT id, titular_id FROM cuenta WHERE cuenta_id = ?0")
    CuentaTitularIdVO findByCuenta_id(String cuentaId);

    /** Listar cuenta por id del tituarl
     * @param titularId
     */
    @Query("SELECT id, cuenta_id FROM cuenta WHERE titular_id = ?0")
    LinkedList<CuentaIdentificadorVO> listarPorTitular(String titularId);

    /** Obtiene el saldo por cuenta y tipo de saldo
     * @param dto
     */
    @Query("SELECT monto FROM saldo WHERE cuenta_cuenta_ID1 = ?0 AND tipo_saldo_tipo_saldo_ID = ?0")
    BigDecimal obtenerSaldo(ConsultarSaldosCargaUtil dto);
}
