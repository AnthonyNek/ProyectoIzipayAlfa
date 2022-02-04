package pe.izipay.pgs.core.domain.dto.cuentas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AplicarSaldoCargaUtil {

    private String cuentaId;
    private int trxId;
    private double importe;
    private double comisionProceso;
    private double impuestoProceso;
    private double comisionEmision;
    private double impuestoEmision;
    /** Obtiene el monto por tipo
     * @param RUBRO
     */
    public double getMontoPorTipo(Rubro RUBRO) {
        switch(RUBRO) {
            case COMISION_EMISION:
                return getComisionEmision();
            case COMISION_PROCESO:
                return getComisionProceso();
            case IMPUESTO_EMISION:
                return getImpuestoEmision();
            case IMPUESTO_PROCESO:
                return getImpuestoProceso();
        }
        return 0;
    }

}
