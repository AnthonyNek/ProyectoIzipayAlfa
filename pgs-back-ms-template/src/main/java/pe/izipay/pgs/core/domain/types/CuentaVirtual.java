package pe.izipay.pgs.core.domain.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaVirtual {
    /** Cuenta Id 1
     */
    public Number cuentaId1;
    /** Cuenta Id
     */
    public Integer cuentaId;
    /** Estado Cuenta
     */
    public char estadoCuenta;
}
