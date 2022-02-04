package pe.izipay.pgs.core.domain.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/** Clase Transsacion
 */
public class Transaccion {
    private Integer trxId;
    private Integer tipoTrx;
}
