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
    public Number cuentaId1;
    public Integer cuentaId;
    public char estadoCuenta;
}
