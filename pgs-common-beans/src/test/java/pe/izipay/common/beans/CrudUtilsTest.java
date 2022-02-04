package pe.izipay.common.beans;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pe.izipay.common.core.exceptions.BaseAppError;
import pe.izipay.common.core.exceptions.AppModuleException;
import pe.izipay.common.core.domainobjects.paginations.FilterPaginationPayload;
import pe.izipay.common.core.domainobjects.paginations.PaginationPayload;
import pe.izipay.common.core.domainobjects.paginations.PaginationResult;
import pe.izipay.common.core.domainobjects.paginations.TotalRecordsResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CrudUtilsTest {
    @Test
    void testVerifyAmbiguity() {
        ArrayList<Object> list = new ArrayList<>();
        assertNull(CrudUtils.verifyAmbiguity(list, new BaseAppError<>()));
    }

    @Test
    void testVerifyAmbiguity2() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertEquals("42", CrudUtils.verifyAmbiguity(objectList, new BaseAppError<>()));
    }

    @Test
    void testVerifyAmbiguity3() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        objectList.add("42");
        var baseError = new BaseAppError<>();
        assertThrows(AppModuleException.class, () -> CrudUtils.verifyAmbiguity(objectList, baseError));
    }

    @Test
    void testCreatePageable() {
        PaginationPayload paginationPayload = new PaginationPayload(1, 1);
        paginationPayload.setNro_pagina(1);
        PageRequest actualCreatePageableResult = CrudUtils.createPageable(paginationPayload);
        assertFalse(actualCreatePageableResult.hasPrevious());
        assertTrue(actualCreatePageableResult.getSort().toList().isEmpty());
        assertEquals(1, actualCreatePageableResult.getPageSize());
    }

    @Test
    void testCreatePageable2() {
        PaginationPayload paginationPayload = new PaginationPayload(1, 1);
        paginationPayload.setNro_pagina(1);

        var filterPaginationPayload = new FilterPaginationPayload<String>();
        filterPaginationPayload.setPaginacion(paginationPayload);
        PageRequest actualCreatePageableResult = CrudUtils.createPageable(filterPaginationPayload);
        assertFalse(actualCreatePageableResult.hasPrevious());
        assertTrue(actualCreatePageableResult.getSort().toList().isEmpty());
        assertEquals(1, actualCreatePageableResult.getPageSize());
    }

    @Test
    void testCreatePaginationResult() {
        PageImpl<Object> pageImpl = new PageImpl<>(new ArrayList<>());
        PaginationResult<Object> actualCreatePaginationResultResult = CrudUtils.createPaginationResult(pageImpl);
        assertEquals(0, actualCreatePaginationResultResult.getCantidad_registros());
        assertEquals(0, actualCreatePaginationResultResult.getTotal_registros());
        assertEquals(1, actualCreatePaginationResultResult.getTotal_paginas());
        assertEquals(1, actualCreatePaginationResultResult.getNro_pagina());
        assertTrue(actualCreatePaginationResultResult.getLista().isEmpty());
        assertTrue(pageImpl.toList().isEmpty());
    }

    @Test
    void testReadTotalRecordsResult() {
        TotalRecordsResult totalRecordsResult = new TotalRecordsResult();
        totalRecordsResult.setCount(3L);
        assertEquals(3L, CrudUtils.readTotalRecordsResult(totalRecordsResult));
    }

    @Test
    void testReadTotalRecordsResult2() {
        assertEquals(0L, CrudUtils.readTotalRecordsResult(null));
    }

    @Test
    void testReadTotalRecordsResultInt() {
        TotalRecordsResult totalRecordsResult = new TotalRecordsResult();
        totalRecordsResult.setCount(3L);
        assertEquals(3, CrudUtils.readTotalRecordsResultInt(totalRecordsResult));
    }

    @Test
    void testReadTotalRecordsResultInt2() {
        assertEquals(0, CrudUtils.readTotalRecordsResultInt(null));
    }

    @Test
    void testToStringObjectId2() {
        assertNull(null);
    }

    @Test
    void testToObjectIdString() {
        assertNull(CrudUtils.toObjectIdString(null));
    }

    @Test
    void testCreatePageableFirst() {
        PageRequest actualCreatePageableFirstResult = CrudUtils.createPageableFirst();
        assertFalse(actualCreatePageableFirstResult.hasPrevious());
        assertTrue(actualCreatePageableFirstResult.getSort().toList().isEmpty());
        assertEquals(1, actualCreatePageableFirstResult.getPageSize());
    }
}

