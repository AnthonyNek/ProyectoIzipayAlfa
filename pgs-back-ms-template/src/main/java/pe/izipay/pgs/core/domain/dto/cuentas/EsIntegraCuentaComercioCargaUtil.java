package pe.izipay.pgs.core.domain.dto.cuentas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EsIntegraCuentaComercioCargaUtil {
    private String hashActual;
    private CalcularHashCuentaComercioCargaUtil calcularHashCargaUtil;
}
