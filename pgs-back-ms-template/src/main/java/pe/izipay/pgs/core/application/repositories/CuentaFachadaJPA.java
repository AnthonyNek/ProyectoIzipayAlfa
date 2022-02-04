package pe.izipay.pgs.core.application.repositories;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.pgs.core.domain.dto.cuentas.ConsultarSaldosCargaUtil;
import pe.izipay.pgs.core.domain.types.CuentaVirtual;
import pe.izipay.pgs.core.domain.types.Saldo;
import pe.izipay.pgs.core.domain.vo.CuentaIdentificadorVO;
import pe.izipay.pgs.core.domain.vo.CuentaTitularIdVO;


import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public interface CuentaFachadaJPA extends CrudRepository<CuentaVirtual, Long> {

    /** Muestra el id y el titular de la cuenta mediante cuenta id
     * @param cuentaId string
     */
    @Query("SELECT id, titular_id FROM cuenta WHERE cuenta_id = ?0")
    CuentaTitularIdVO findByCuenta_id(String cuentaId);

    /** Lista la cuenta por titular
     * @param titularId string
     */
    @Query("SELECT id, cuenta_id FROM cuenta WHERE titular_id = ?0")
    LinkedList<CuentaIdentificadorVO> listarPorTitular(String titularId);

    /** Obtiene el valor del saldo mediante la cuenta y el tipo de saldo
     * @param cuenta Integer
     * @param codTipoSaldo Integer
     */
    @Query("SELECT valor FROM saldo_actual WHERE cuenta = ?0 AND codigotiposaldo = ?0")
    BigDecimal obtenerSaldo(Integer cuenta, Integer codTipoSaldo);

    /** lista los saldos por el ido de cuenta
     * @param idCuenta Integer
     */
    @Query("SELECT saldo_ID, codigotiposaldo, valor FROM saldo_actual WHERE cuenta = ?0")
    List<Saldo> listarSaldosPorCuenta(Integer idCuenta);
}
