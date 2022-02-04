package pe.izipay.pgs.core.infrastructure.repositories.jpa;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.seeddata.CuentaSeedData;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@EnableAutoConfiguration
@SpringBootTest(classes = {EsquemaBD.class})
class RestaurarBDTests {

    @Autowired
    CuentaSeedData cuentaSeedData;

    @Test
    void ejecutar() {
        long cantidad = cuentaSeedData.cargarExcel();
        log.info("Cuentas: {} registro(s) agregado(s)", cantidad);

        assertThat(cantidad).isPositive();
    }

}
