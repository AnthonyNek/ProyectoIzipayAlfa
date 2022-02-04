package pe.izipay.common.beans.repositories.mongodb;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;

import pe.izipay.common.beans.CrudUtils;
import pe.izipay.common.beans.repositories.mongodb.CrudMongoDbRepository;
import pe.izipay.common.beans.repositories.mongodb.FacadeMongoDbRepository;
import pe.izipay.common.core.exceptions.BaseAppError;
import pe.izipay.common.core.types.errors.CommonErrorType;
import pe.izipay.common.core.exceptions.CommonModuleException;
import pe.izipay.common.core.exceptions.AppModuleException;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;
import pe.izipay.common.core.domainobjects.audit.UserAuditData;
import pe.izipay.common.core.types.DataOriginType;
import pe.izipay.common.core.types.DataStatusType;
import pe.izipay.common.core.types.errors.GenericErrorType;

import static org.mockito.Mockito.mock;

class CrudMongoDbRepositoryTest {
    CrudMongoDbRepository c;

    @BeforeEach
    void init() {
        c = new CrudMongoDbRepositoryImpl();
    }

    @Test
    void testRecordVerification() {
        assertEquals("data", CrudUtils.verifyRecord("data"));
        assertEquals("data", CrudUtils.verifyRecord("data", new BaseAppError<>()));

        var ex = assertThrows(CommonModuleException.class, () -> CrudUtils.verifyRecord(null));
        assertEquals(CommonErrorType.RECORD_NOT_FOUND, ex.getError());

        var listErrors = new ArrayList<IReadOnlyError<?>>(1);
        listErrors.add(GenericErrorType.DEFAULT);
        var ex2 = assertThrows(AppModuleException.class, () -> CrudUtils.verifyRecord(null, GenericErrorType.DEFAULT));
        assertEquals(ex2.getErrors(), listErrors);
    }

    @Test
    void testGetModel() {
        var id = c.generateObjectId();
        CommonModuleException ex = assertThrows(CommonModuleException.class, () -> c.getModelById(id));
        assertEquals(CommonErrorType.RECORD_NOT_FOUND, ex.getError());
    }

    @Test
    void testSlice() {
        var list = new ArrayList<Integer>();
        list.add(1);
        var slice  = new SliceImpl(list, Pageable.unpaged(),false);
        assertEquals(1,c.getFirst(slice));
    }

    private static class CrudMongoDbRepositoryImpl extends CrudMongoDbRepository {

        @Override
        protected FacadeMongoDbRepository getMongoDbFacade() {
            return mock(FacadeMongoDbRepository.class);
        }
    }
}
