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

    @Query("SELECT id, titular_id FROM cuenta WHERE cuenta_id = ?0")
    CuentaTitularIdVO findByCuenta_id(String cuentaId);

    @Query("SELECT id, cuenta_id FROM cuenta WHERE titular_id = ?0")
    LinkedList<CuentaIdentificadorVO> listarPorTitular(String titularId);

    @Query("SELECT valor FROM saldo_actual WHERE cuenta = ?0 AND codigotiposaldo = ?0")
    BigDecimal obtenerSaldo(Integer cuenta, Integer codTipoSaldo);

    @Query("SELECT saldo_ID, codigotiposaldo, valor FROM saldo_actual WHERE cuenta = ?0")
    List<Saldo> listarSaldosPorCuenta(Integer idCuenta);
}
