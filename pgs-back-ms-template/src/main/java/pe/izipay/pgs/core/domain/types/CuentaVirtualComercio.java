package pe.izipay.pgs.core.domain.types;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/** Clase Cuenta Comercio
 */
public class CuentaVirtualComercio extends CuentaVirtual{
    /** Id del comercio
     */
    @NonNull
    public Integer comercioId;

    /** Numero de la cuenta virtual maestra
     */
    @NonNull
    public String numeroCuentaVirtualComercio;

    /** Estado de la cuenta virtual
     */
    @NonNull
    public char estadoCuentaVirtual;

    /** Condicion Operativa
     */
    @NonNull
    public String condicionOperativa;

    /** Id del comercio 1
     */
    @NonNull
    public Number comercioComercioId1;

    /** Id de la  cuenta
     */
    @NonNull
    public Integer cuentaId;
}


