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
public class CalcularHashCuentaComercioCargaUtil {
    private String idCuenta;
    private double saldoEfectivo;
    private double saldoIGV;
    private double saldoRetenido;
    private Moneda moneda;
    private Date ultimaFechaTransaccion;
}
