package pe.izipay.pgs.core.application.services.cuentas.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pe.izipay.pgs.core.application.repositories.CuentaComercioRepositorio;
import pe.izipay.pgs.core.application.repositories.RepositorioSaldosTransaccion;
import pe.izipay.pgs.core.application.services.cuentas.ConsultarSaldosCuentaServicio;
import pe.izipay.pgs.core.application.services.cuentas.EsIntegraCuentaComercioServicio;
import pe.izipay.pgs.core.domain.dto.cuentas.*;
import pe.izipay.pgs.core.domain.types.Saldo;
import pe.izipay.pgs.core.domain.types.TipoSaldo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AplicarSaldosCuentaServicioGenericoImplTest {

    private static RepositorioSaldosTransaccion repositorio;
    private static ConsultarSaldosCuentaServicio servicioConsulta;
    private static AplicarSaldosCuentaServicioGenericoImpl servicio;


    @BeforeAll
    static void setUp() {
        repositorio = new RepositorioSaldosTransaccion() {
            @Override
            public Collection<SaldoTransaccion> obtenerSaldosTransacciones(int idTransaccion)
            {
                ArrayList<SaldoTransaccion> saldos = new ArrayList<>();
                saldos.add(new SaldoTransaccion(TipoSaldo.SALDO_EFECTIVO, Rubro.COMISION_PROCESO, TipoOperacion.DECREMENTAR));
                saldos.add(new SaldoTransaccion(TipoSaldo.SALDO_EFECTIVO, Rubro.IMPORTE, TipoOperacion.INCREMENTAR));
                return saldos;
            }
        };
        servicioConsulta = new ConsultarSaldosCuentaServicio() {
            @Override
            public List<Saldo> consultarSaldosCuenta(Integer idCuenta) {
                ArrayList<Saldo> saldos = new ArrayList<>();
                saldos.add(new Saldo(1, TipoSaldo.SALDO_EFECTIVO, 2000));
                saldos.add(new Saldo(2, TipoSaldo.SALDO_IGV, 200));
                return saldos;
            }
            @Override
            public BigDecimal consultarSaldosCuenta(Integer cuenta, Integer codTipoSaldo) {
                return null;
            }
        };
        servicio = new AplicarSaldosCuentaServicioGenericoImpl(repositorio, servicioConsulta);
    }

    @Test
    void aplicarSaldoComercioCase1() {
        Collection<SaldoAfectado> saldosAfectados =
        servicio.aplicarSaldosCuentaComercio(new AplicarSaldoCargaUtil(
                "11111111",
                1,
                1000,
                50,
                0,
                0,
                0
        ));
        Collection<SaldoAfectado> expectedOutput = new ArrayList<>();
        expectedOutput.add(
                new SaldoAfectado("11111111", Rubro.COMISION_PROCESO, TipoSaldo.SALDO_EFECTIVO, 2000, 1950, 50, TipoOperacion.DECREMENTAR)
        );
        expectedOutput.add(
                new SaldoAfectado("11111111", Rubro.IMPORTE, TipoSaldo.SALDO_EFECTIVO, 1950, 2950, 1000, TipoOperacion.INCREMENTAR)
        );
        System.out.println(saldosAfectados);
        assertEquals(expectedOutput, saldosAfectados);
    }

}
