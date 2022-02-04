package pe.izipay.pgs.core.infrastructure.repositories.jpa.modelos;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.EsquemaBD;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = EsquemaBD.Tabla.CUENTAS)
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20, unique = true)
    private String cuenta_id;

    @Column(length = 10)
    private String titular_id;

    @Column(length = 15)
    private String comercio_id;

    @Column(length = 10)
    private String tipo_cuenta;

    @Column(length = 5)
    private String moneda;

    @Column(length = 5)
    private String estado;

    @Column(length = EsquemaBD.VARCHAR_LONGITUD_DEFECTO, unique = true)
    private String usuario;

    LocalDateTime fecha_solictud;
}
