package pe.izipay.pgs.core.domain.types;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaVirtualComercio extends CuentaVirtual{
    @NonNull
    public Integer comercioId;

    @NonNull
    public String numeroCuentaVirtualComercio;

    @NonNull
    public char estadoCuentaVirtual;

    @NonNull
    public String condicionOperativa;

    @NonNull
    public Number comercioComercioId1;

    @NonNull
    public Integer cuentaId;
}


