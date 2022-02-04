package pe.izipay.pgs.core.infrastructure.repositories.jpa;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class EsquemaBD {

    public static final byte VARCHAR_LONGITUD_DEFECTO = 50;
    public static final byte BIGINT_LONGITUD_DEFECTO = 20;
    public static final byte SMALLINT_LONGITUD_DEFECTO = 5;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Columna {
        public static final String ID = "id";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Tabla {
        public static final String CUENTAS = "cuenta";
        public static final String CONDICIONES_OPERATIVAS = "condicion_operativa";
    }
}