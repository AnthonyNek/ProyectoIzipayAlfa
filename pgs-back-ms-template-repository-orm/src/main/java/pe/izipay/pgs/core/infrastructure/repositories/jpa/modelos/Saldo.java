package pe.izipay.pgs.core.infrastructure.repositories.jpa.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Saldo {
    private Integer saldoId;
    private Integer tipoSaldo;
    private BigDecimal valor;
}
