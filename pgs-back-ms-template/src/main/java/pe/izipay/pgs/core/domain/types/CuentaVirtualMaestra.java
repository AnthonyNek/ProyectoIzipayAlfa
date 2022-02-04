package pe.izipay.pgs.core.domain.types;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/** Clase Cuenta Virtual Maestra
 */
public class CuentaVirtualMaestra extends CuentaVirtual{
    /** Id de la cuenta 1
     */
    @NonNull
    public Number cuentaCuentaId1;

    /** Id del titular
     */
    @NonNull
    public String titularId;

    /** Numero de la cuenta virtual maestra
     */
    @NonNull
    public String numeroCuentaVirtualMaestra;

    /** Estado de la cuenta mestra
     */
    @NonNull
    public char estadoCuentaMaestra;

    /** Condicion Operativa
     */
    @NonNull
    public String condicionOperativa;

    /** Titular Id
     */
    @NonNull
    public Number titularTitularId1;

    /** Cuenta Id
     */
    @NonNull
    public Integer cuentaId;

}
