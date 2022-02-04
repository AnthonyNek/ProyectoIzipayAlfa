package pe.izipay.pgs.core.domain.dto.cuentas;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalcularHashCuentaMaestraCargaUtil {
    private Integer idCuenta;
    private BigDecimal saldoEfectivo;
    private BigDecimal saldoIGV;
    private  BigDecimal saldoRetenido;
   // private BigDecimal saldoDisponible;
    private BigDecimal saldoRebate;
    private Data ultFechaTtrx;
}
