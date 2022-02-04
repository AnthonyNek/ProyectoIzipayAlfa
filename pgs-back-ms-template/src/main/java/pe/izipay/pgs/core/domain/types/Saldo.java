package pe.izipay.pgs.core.domain.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/** Clase Saldo
 */
public class Saldo {
    private Integer saldoId;
    private TipoSaldo tipoSaldo;
    private double monto;
}
