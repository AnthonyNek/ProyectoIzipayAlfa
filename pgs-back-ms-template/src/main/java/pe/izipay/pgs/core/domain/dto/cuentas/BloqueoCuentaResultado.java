package pe.izipay.pgs.core.domain.dto.cuentas;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
@Setter
@Getter
public class BloqueoCuentaResultado {

    private String estado_anterior;
}
