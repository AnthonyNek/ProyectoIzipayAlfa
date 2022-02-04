package pe.izipay.pgs.core.application.services.cuentas.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pe.izipay.pgs.core.application.repositories.CuentaFachadaJPA;
import pe.izipay.pgs.core.domain.types.Saldo;
import pe.izipay.pgs.core.domain.types.TipoSaldo;
import pe.izipay.pgs.core.domain.vo.CuentaTitularIdVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConsultarSaldosCuentaServicioImplTest {

    private static ConsultarSaldosCuentaServicioImpl servicio;

    @BeforeAll
    static void setUp(){
        CuentaFachadaJPA repositorio = new CuentaFachadaJPA() {
            @Override
            public CuentaTitularIdVO findByCuenta_id(String cuentaId) {
                CuentaTitularIdVO cuenta = new CuentaTitularIdVO(cuentaId, "12234");
                return cuenta;
            }

            @Override
            public BigDecimal obtenerSaldo(Integer cuenta, Integer codTipoSaldo) {
                BigDecimal saldo = new BigDecimal(104.80);
                return saldo;
            }

            @Override
            public List<Saldo> listarSaldosPorCuenta(Integer idCuenta) {
                ArrayList<Saldo> saldos = new ArrayList<>();
                saldos.add(new Saldo(1, TipoSaldo.SALDO_EFECTIVO, 201.90));
                saldos.add(new Saldo(2, TipoSaldo.SALDO_RETENIDO, 20));
                saldos.add(new Saldo(2, TipoSaldo.SALDO_IGV, 81.70));
                return saldos;
            }
        };

        servicio= new ConsultarSaldosCuentaServicioImpl(repositorio);
    }


    // Consulta de todos los saldos de una cuenta

    @Test
    void consultaSaldosCuentaVacia(){
       List<Saldo> saldos = servicio.consultarSaldosCuenta(100243423);
        assertFalse(saldos.isEmpty());
    }


    @Test
    void consultaSaldoEncontrado(){
        BigDecimal saldo = servicio.consultarSaldosCuenta(100243423, 1);
        assertEquals(104.80,104.80);
    }

}
