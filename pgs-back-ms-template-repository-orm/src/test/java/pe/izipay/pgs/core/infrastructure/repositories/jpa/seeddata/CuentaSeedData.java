package pe.izipay.pgs.core.infrastructure.repositories.jpa.seeddata;

import org.springframework.stereotype.Component;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.fachadas.CuentaFachadaJPA;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.modelos.Cuenta;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.seeddata.shared.BaseSeedData;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.seeddata.shared.ISeedData;

@Component
public class CuentaSeedData extends BaseSeedData<Cuenta, Long, CuentaFachadaJPA> implements ISeedData {

    public CuentaSeedData(CuentaFachadaJPA fachada) {
        super(Cuenta.class, fachada, obtenerRecurso("cuentas.xlsx"));
    }
}
