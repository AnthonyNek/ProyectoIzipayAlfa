package pe.izipay.common.beans.repositories.mongodb;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mongodb.BasicDBObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Function;

import org.bson.types.ObjectId;

import org.junit.jupiter.api.Test;
import pe.izipay.common.beans.repositories.mongodb.MongoDbQueryBuilderFacade;

class MongoDbQueryBuilderFacadeTest {
    @Test
    void testConstructor() {
        MongoDbQueryBuilderFacade actualMongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertTrue(actualMongoDbQueryBuilderFacade.isEmpty());
        assertTrue(actualMongoDbQueryBuilderFacade.getQuery().isEmpty());
    }

    @Test
    void testAddNotDeleted() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addNotDeleted());
    }

    @Test
    void testAdd() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        Function<String, Object> function = (Function<String, Object>) mock(Function.class);
        when(function.apply(any())).thenReturn("Apply");
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.add("Field", "42", function));
        verify(function).apply(any());
    }

    @Test
    void testAdd2() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        Function<String, Object> function = (Function<String, Object>) mock(Function.class);
        when(function.apply(any())).thenReturn("Apply");
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.add("Field", "", function));
    }

    @Test
    void testAddIfHasValue() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addIfHasValue("Field", "42"));
    }

    @Test
    void testAddIfHasValue2() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addIfHasValue("Field", ""));
    }

    @Test
    void testAddObjectId() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addObjectId("Field", ""));
    }

    @Test
    void testAddContainsInsentive() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addContainsInsentive("Field", "42"));
    }

    @Test
    void testAddContainsInsentive2() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addContainsInsentive("Field", ""));
    }

    @Test
    void testAddAllContainsInsentive() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addAllContainsInsentive("42", "Fields"));
    }

    @Test
    void testAddAllContainsInsentive2() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addAllContainsInsentive("", "Fields"));
    }

    @Test
    void testAddAll() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addAll("Field", new HashSet<>()));
    }

    @Test
    void testAddAll2() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();

        HashSet<ObjectId> objectIdSet = new HashSet<>();
        objectIdSet.add(ObjectId.get());
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addAll("Field", objectIdSet));
    }

    @Test
    void testAddAllObjectId() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addAllObjectId("Field", new ArrayList<>()));
    }

    @Test
    void testAddAllObjectId2() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addAllObjectId("Field", new ArrayList<>(),
                (Function<Object, ObjectId>) mock(Function.class)));
    }

    @Test
    void testAddAllObjectId3() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();

        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        Function<Object, ObjectId> function = (Function<Object, ObjectId>) mock(Function.class);
        when(function.apply(any())).thenReturn(ObjectId.get());
        assertSame(mongoDbQueryBuilderFacade, mongoDbQueryBuilderFacade.addAllObjectId("Field", objectList, function));
        verify(function).apply(any());
    }

    @Test
    void testIsEmpty() {
        assertTrue((new MongoDbQueryBuilderFacade()).isEmpty());
    }

    @Test
    void testIsEmpty2() {
        MongoDbQueryBuilderFacade mongoDbQueryBuilderFacade = new MongoDbQueryBuilderFacade();
        mongoDbQueryBuilderFacade.addNotDeleted();
        assertFalse(mongoDbQueryBuilderFacade.isEmpty());
    }

    @Test
    void testBuild() {
        assertTrue(((BasicDBObject) (new MongoDbQueryBuilderFacade()).build()).isEmpty());
    }
}

