package pe.izipay.pgs.core.domain.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/** Clase Saldo Transaccion
 */
public class SaldosTransaccion {
    private Integer trxId;
    private Integer saldoId;
    private String tipoCuenta;
    private String afecta;
}
