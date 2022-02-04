package pe.izipay.pgs.core.infrastructure.repositories.jpa.modelos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaVirtualMaestra extends CuentaVirtual{
    @NonNull
    public Number cuentaCuentaId1;

    @NonNull
    public String titularId;

    @NonNull
    public String numeroCuentaVirtualMaestra;

    @NonNull
    public char estadoCuentaMaestra;

    @NonNull
    public String condicionOperativa;

    @NonNull
    public Number titularTitularId1;

    @NonNull
    public Integer cuentaId;

}
