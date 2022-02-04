package pe.izipay.pgs.core.application.services.cuentas.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.izipay.pgs.core.application.repositories.CuentaComercioRepositorio;
import pe.izipay.pgs.core.application.services.cuentas.EsIntegraCuentaComercioServicio;
import pe.izipay.pgs.core.domain.dto.cuentas.CalcularHashCuentaComercioCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.EsIntegraCuentaComercioCargaUtil;
import pe.izipay.pgs.core.domain.dto.cuentas.Moneda;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EsIntegraCuentaComercioServicioImplTest {

    public static class CuentaComercioRepoMock implements CuentaComercioRepositorio {
        @Override
        public void guardarHash(String idCuenta, String newHash) {
        }
    }

    private static CuentaComercioRepositorio repositorio;
    private static EsIntegraCuentaComercioServicio servicio;

    @BeforeAll
    static void setUp() {
        repositorio = new CuentaComercioRepoMock();
        servicio = new EsIntegraCuentaComercioServicioImpl(repositorio);
    }

    @Test
    void esIntegraCuentaNoIntegra() throws JsonProcessingException {
        EsIntegraCuentaComercioCargaUtil cargaUtil = new EsIntegraCuentaComercioCargaUtil(
                "ccc1928b1bffd5b1236049a7215470d7c7c5339d04f1cd55a98d33b24a3fafe2",
                new CalcularHashCuentaComercioCargaUtil(
                        "00000001",
                        1000.0,
                        100.0,
                        200.0,
                        Moneda.PEN,
                        new Date(1000)
                )
        );
        boolean esIntegra = servicio.esIntegra(cargaUtil);
        assertTrue(!esIntegra);
    }

    @Test
    void esIntegraCuentaIntegra() throws JsonProcessingException {
        EsIntegraCuentaComercioCargaUtil cargaUtil = new EsIntegraCuentaComercioCargaUtil(
                "ccc1928b1bd5b1236049baa7215470d7c7c5339d04f1cd55a98d33b24a3fafe2",
                new CalcularHashCuentaComercioCargaUtil(
                        "00000001",
                        1000.0,
                        100.0,
                        200.0,
                        Moneda.PEN,
                        new Date(1000)
                )
        );
        boolean esIntegra = servicio.esIntegra(cargaUtil);
        assertTrue(esIntegra);
    }
}



