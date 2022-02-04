package pe.izipay.pgs.core.domain.dto.cuentas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalcularHashCuentaMaestraCargaUtil {
    private String idCuenta;
    private double saldoEfectivo;
    private double saldoIGV;
    private double saldoRetenido;
    private double saldoRebate;
    private Moneda moneda;
    private Date ultimaFechaTransaccion;
}
