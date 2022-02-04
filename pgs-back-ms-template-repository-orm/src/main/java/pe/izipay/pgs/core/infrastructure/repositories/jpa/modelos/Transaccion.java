package pe.izipay.pgs.core.infrastructure.repositories.jpa.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion {
    private Integer trxId;
    private Integer tipoTrx;
}
