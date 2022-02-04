package pe.izipay.common.core.actions;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.paginations.PaginationPayload;
import pe.izipay.common.core.domainobjects.paginations.PaginationResult;

import static org.junit.jupiter.api.Assertions.*;

class PaginationBuilderTest {
    @Test
    void testTotalRegistros() {
        assertTrue((new PaginationBuilder<>()).totalRegistros(1));
        assertFalse((new PaginationBuilder<>()).totalRegistros(0));
    }

    @Test
    void testPaginationPayload() {
        PaginationBuilder<Object> paginationBuilder = new PaginationBuilder<>();

        PaginationPayload paginationPayload = new PaginationPayload(1, 1);
        paginationPayload.setCantidad_registros(0);
        assertSame(paginationBuilder, paginationBuilder.paginationPayload(paginationPayload));
    }

    @Test
     void testLista() {
        PaginationBuilder<Object> paginationBuilder = new PaginationBuilder<>();
        assertDoesNotThrow(() ->
                paginationBuilder.lista(new ArrayList<>())
        );
    }

    @Test
    void testBuild() {
        var builder = new PaginationBuilder<>();
        assertThrows(ArithmeticException.class, builder::build);
    }

    @Test
    void testBuild2() {
        PaginationBuilder<Object> paginationBuilder = new PaginationBuilder<>();
        paginationBuilder.paginationPayload(new PaginationPayload(1, 1));
        PaginationResult<Object> actualBuildResult = paginationBuilder.build();
        assertEquals(0, actualBuildResult.getTotal_paginas());
        assertEquals(0, actualBuildResult.getNro_pagina());
    }

    @Test
    void testBuild3() {
        PaginationBuilder<Object> paginationBuilder = new PaginationBuilder<>();
        paginationBuilder.totalRegistros(1);
        paginationBuilder.paginationPayload(new PaginationPayload(1, 1));
        Assertions.assertEquals(1, paginationBuilder.build().getTotal_paginas());
    }

    @Test
    void testBuild4() {
        PaginationBuilder<Object> paginationBuilder = new PaginationBuilder<>();
        paginationBuilder.totalRegistros(1);
        paginationBuilder.paginationPayload(new PaginationPayload(Integer.MIN_VALUE, 1));
        Assertions.assertEquals(1, paginationBuilder.build().getTotal_paginas());
    }
}

