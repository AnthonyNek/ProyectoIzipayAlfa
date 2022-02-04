package pe.izipay.pgs.core.application.services.cuentas.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pe.izipay.pgs.core.application.repositories.CuentaComercioRepositorio;
import pe.izipay.pgs.core.application.repositories.CuentaMaestraRepositorio;
import pe.izipay.pgs.core.application.services.cuentas.EsIntegraCuentaComercioServicio;
import pe.izipay.pgs.core.domain.dto.cuentas.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EsIntegraCuentaMaestraServicioImplTest {

    private static CuentaMaestraRepositorio repositorio;
    private static EsIntegraCuentaMaestraServicioImpl servicio;

    @BeforeAll
    static void setUp() {
        repositorio = new CuentaMaestraRepositorio() {
            @Override
            public void guardarHash(String idCuenta, String newHash) {
            }
        };
        servicio = new EsIntegraCuentaMaestraServicioImpl(repositorio);
    }

    @Test
    void esIntegraCuentaNoIntegra() throws JsonProcessingException {
        EsIntegraCuentaMaestraCargaUtil cargaUtil = new EsIntegraCuentaMaestraCargaUtil(
                "cd911f75aeff88c2a9ebffcf56e10a952dab828a2ee0a2144b9253079e7eae8a",
                new CalcularHashCuentaMaestraCargaUtil(
                        "00000001",
                        1000.0,
                        100.0,
                        200.0,
                        100.0,
                        Moneda.PEN,
                        new Date(1000)
                )
        );
        boolean esIntegra = servicio.esIntegra(cargaUtil);
        assertTrue(!esIntegra);
    }

    @Test
    void esIntegraCuentaIntegra() throws JsonProcessingException {
        EsIntegraCuentaMaestraCargaUtil cargaUtil = new EsIntegraCuentaMaestraCargaUtil(
                "cd911f75aeff88c2a9ebcbcf56e10a952dab828a2ee0a2144b9253079e7eae8a",
                new CalcularHashCuentaMaestraCargaUtil(
                        "00000001",
                        1000.0,
                        100.0,
                        200.0,
                        100.0,
                        Moneda.PEN,
                        new Date(1000)
                )
        );
        boolean esIntegra = servicio.esIntegra(cargaUtil);
        assertTrue(esIntegra);
    }
}



