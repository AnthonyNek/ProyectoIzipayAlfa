package pe.izipay.pgs.core.infrastructure.repositories.jpa.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.fachadas.CuentaFachadaJPA;
import pe.izipay.pgs.core.infrastructure.repositories.jpa.mapeadores.CuentaMapeador;

class CuentaRepositorioJPATests {

    private static CuentaFachadaJPA fachada;
    private static CuentaMapeador mapeador;
    private static CuentaRepositorioJPA repositorio;

    @BeforeAll
    static void setUp() {
        fachada = Mockito.mock(CuentaFachadaJPA.class);
        mapeador = Mockito.mock(CuentaMapeador.class);
        repositorio = new CuentaRepositorioJPA(fachada, mapeador);
    }

    @Test
    void listarTodos() {

    }

}
