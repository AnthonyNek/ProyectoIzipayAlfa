package pe.izipay.pgs.core.application.services.cuentas;

import pe.izipay.pgs.core.domain.vo.CuentaIdentificadorVO;

import java.util.List;

public interface ObtenerRelacionEntreCuentasServicio {

    List<CuentaIdentificadorVO> ejecutar(String cuentaId);
}
