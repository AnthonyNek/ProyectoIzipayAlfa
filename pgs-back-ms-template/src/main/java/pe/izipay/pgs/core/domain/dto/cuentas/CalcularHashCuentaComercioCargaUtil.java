package pe.izipay.pgs.core.domain.dto.cuentas;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalcularHashCuentaComercioCargaUtil {
    private Integer idCcuenta;
    private BigDecimal saldoEfectivo;
    private BigDecimal saldoIGV;
    private  BigDecimal saldoRetenido;
    // private BigDecimal saldoDisponible;
    private Data ultFechaTrx;
}
