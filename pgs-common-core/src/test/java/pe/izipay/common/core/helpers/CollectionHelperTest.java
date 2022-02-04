package pe.izipay.common.core.helpers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.BaseDomainObject;
import pe.izipay.common.core.exceptions.CommonModuleException;
import pe.izipay.common.core.interfaces.IDomainObject;
import pe.izipay.common.core.domainobjects.batchs.BatchFailResult;

class CollectionHelperTest {
    @Test
    void testSize() {
        assertEquals(0, CollectionHelper.size(new ArrayList<>()));
        assertEquals(0, CollectionHelper.size(null));
    }

    @Test
    void testFirstOrDefaultObj() {
        assertNull(CollectionHelper.firstOrDefaultObj(new ArrayList<>(), "Obj"));
        assertNull(CollectionHelper.firstOrDefaultObj(new ArrayList<>(), "Obj"));
    }

    @Test
    void testFirstOrDefaultObj2() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertNull(CollectionHelper.firstOrDefaultObj(objectList, "Obj"));
    }

    @Test
    void testFirstOrDefaultObj3() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertEquals("42", CollectionHelper.firstOrDefaultObj(objectList, "42"));
    }

    @Test
    void testFirstOrDefaultObj4() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertNull(CollectionHelper.firstOrDefaultObj(objectList, "Obj"));
    }

    @Test
    void testFirstOrDefaultObj5() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertEquals("42", CollectionHelper.firstOrDefaultObj(objectList, "42"));
    }

    @Test
    void testFirstObj() {
        var list = new ArrayList<>();
        assertThrows(CommonModuleException.class, () -> CollectionHelper.firstObj(list, "Obj"));
    }

    @Test
    void testFirstObj2() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertThrows(CommonModuleException.class, () -> CollectionHelper.firstObj(objectList, "Obj"));
    }

    @Test
    void testFirstObj3() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertEquals("42", CollectionHelper.firstObj(objectList, "42"));
    }

    @Test
    void testFirstObj4() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertThrows(CommonModuleException.class, () -> CollectionHelper.firstObj(objectList, "Obj"));
    }

    @Test
    void testFirstObj5() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertEquals("42", CollectionHelper.firstObj(objectList, "42"));
    }


    @Test
    void testFirstOrDefault() {
        assertNull(CollectionHelper.firstOrDefault(new ArrayList<>(), "Id"));
        assertNull(CollectionHelper.firstOrDefault(new ArrayList<>(), (BiPredicate<Object, Object>) mock(BiPredicate.class),
                "Filter"));
        assertNull(CollectionHelper.firstOrDefault((Collection<Object>) new ArrayList<>(), (Predicate<Object>) mock(Predicate.class)));
        assertNull(CollectionHelper.firstOrDefault(new ArrayList<>(), "Id"));
        assertNull(CollectionHelper.firstOrDefault(new ArrayList<>(), (BiPredicate<Object, Object>) mock(BiPredicate.class),
                "Filter"));
        assertNull(CollectionHelper.firstOrDefault(new ArrayList<IDomainObject<Predicate<Object>>>(), (Predicate<Object>) mock(Predicate.class)));
    }

    @Test
    void testFirstOrDefault2() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        assertNull(CollectionHelper.firstOrDefault(iDomainObjectList, "Id"));
    }

    @Test
    void testFirstOrDefault3() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        assertNull(CollectionHelper.firstOrDefault(iDomainObjectList,
                new BaseDomainObject.StringDomainObject()));
    }

    @Test
    void testFirstOrDefault4() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        assertTrue(CollectionHelper.firstOrDefault(iDomainObjectList, (Object) null) instanceof BatchFailResult);
    }

    @Test
    void testFirstOrDefault5() {
        BatchFailResult<Object> batchFailResult = new BatchFailResult<>();
        batchFailResult.setId("Id");

        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(batchFailResult);
        assertNull(CollectionHelper.firstOrDefault(iDomainObjectList,
                new BaseDomainObject.StringDomainObject()));
    }

    @Test
    void testFirstOrDefault6() {
        BatchFailResult<Object> batchFailResult = new BatchFailResult<>();
        batchFailResult.setId(new BaseDomainObject.StringDomainObject());

        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(batchFailResult);
        assertTrue(CollectionHelper.firstOrDefault(iDomainObjectList,
                new BaseDomainObject.StringDomainObject()) instanceof BatchFailResult);
    }

    @Test
    void testFirstOrDefault7() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        BiPredicate<Object, Object> biPredicate = (BiPredicate<Object, Object>) mock(BiPredicate.class);
        when(biPredicate.test(any(), any())).thenReturn(true);
        assertEquals("42", CollectionHelper.firstOrDefault(objectList, biPredicate, "Filter"));
        verify(biPredicate).test(any(), any());
    }

    @Test
    void testFirstOrDefault8() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        BiPredicate<Object, Object> biPredicate = (BiPredicate<Object, Object>) mock(BiPredicate.class);
        when(biPredicate.test(any(), any())).thenThrow(new CommonModuleException("An error occurred"));
        assertThrows(CommonModuleException.class, () -> CollectionHelper.firstOrDefault(objectList, biPredicate, "Filter"));
        verify(biPredicate).test(any(), any());
    }

    @Test
    void testFirstOrDefault9() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        BiPredicate<Object, Object> biPredicate = (BiPredicate<Object, Object>) mock(BiPredicate.class);
        when(biPredicate.test(any(), any())).thenReturn(false);
        assertNull(CollectionHelper.firstOrDefault(objectList, biPredicate, "Filter"));
        verify(biPredicate).test(any(), any());
    }

    @Test
    void testFirstOrDefault10() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        Predicate<Object> predicate = (Predicate<Object>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(true);
        assertEquals("42", CollectionHelper.firstOrDefault(objectList, predicate));
        verify(predicate).test(any());
    }

    @Test
    void testFirstOrDefault11() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        Predicate<Object> predicate = (Predicate<Object>) mock(Predicate.class);
        when(predicate.test(any())).thenThrow(new CommonModuleException("An error occurred"));
        assertThrows(CommonModuleException.class, () -> CollectionHelper.firstOrDefault(objectList, predicate));
        verify(predicate).test(any());
    }

    @Test
    void testFirstOrDefault12() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        Predicate<Object> predicate = (Predicate<Object>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(false);
        assertNull(CollectionHelper.firstOrDefault(objectList, predicate));
        verify(predicate).test(any());
    }

    @Test
    void testFirstOrDefault13() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        assertNull(CollectionHelper.firstOrDefault(iDomainObjectList, "Id"));
    }

    @Test
    void testFirstOrDefault14() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        assertNull(CollectionHelper.firstOrDefault(iDomainObjectList,
                new BaseDomainObject.StringDomainObject()));
    }

    @Test
    void testFirstOrDefault15() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        assertTrue(CollectionHelper.firstOrDefault(iDomainObjectList, (Object) null) instanceof BatchFailResult);
    }

    @Test
    void testFirstOrDefault16() {
        BatchFailResult<Object> batchFailResult = new BatchFailResult<>();
        batchFailResult.setId("Id");

        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(batchFailResult);
        assertNull(CollectionHelper.firstOrDefault(iDomainObjectList,
                new BaseDomainObject.StringDomainObject()));
    }

    @Test
    void testFirstOrDefault17() {
        BatchFailResult<Object> batchFailResult = new BatchFailResult<>();
        batchFailResult.setId(new BaseDomainObject.StringDomainObject());

        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(batchFailResult);
        assertTrue(CollectionHelper.firstOrDefault(iDomainObjectList,
                new BaseDomainObject.StringDomainObject()) instanceof BatchFailResult);
    }

    @Test
    void testFirstOrDefault18() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        BiPredicate<Object, Object> biPredicate = (BiPredicate<Object, Object>) mock(BiPredicate.class);
        when(biPredicate.test(any(), any())).thenReturn(true);
        assertEquals("42", CollectionHelper.firstOrDefault(objectList, biPredicate, "Filter"));
        verify(biPredicate).test(any(), any());
    }

    @Test
    void testFirstOrDefault19() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        BiPredicate<Object, Object> biPredicate = (BiPredicate<Object, Object>) mock(BiPredicate.class);
        when(biPredicate.test(any(), any())).thenThrow(new CommonModuleException("An error occurred"));
        assertThrows(CommonModuleException.class, () -> CollectionHelper.firstOrDefault(objectList, biPredicate, "Filter"));
        verify(biPredicate).test(any(), any());
    }

    @Test
    void testFirstOrDefault20() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        BiPredicate<Object, Object> biPredicate = (BiPredicate<Object, Object>) mock(BiPredicate.class);
        when(biPredicate.test(any(), any())).thenReturn(false);
        assertNull(CollectionHelper.firstOrDefault(objectList, biPredicate, "Filter"));
        verify(biPredicate).test(any(), any());
    }

    @Test
    void testFirstOrDefault21() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        Predicate<Object> predicate = (Predicate<Object>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(true);
        assertEquals("42", CollectionHelper.firstOrDefault(objectList, predicate));
        verify(predicate).test(any());
    }

    @Test
    void testFirstOrDefault22() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        Predicate<Object> predicate = (Predicate<Object>) mock(Predicate.class);
        when(predicate.test(any())).thenThrow(new CommonModuleException("An error occurred"));
        assertThrows(CommonModuleException.class, () -> CollectionHelper.firstOrDefault(objectList, predicate));
        verify(predicate).test(any());
    }

    @Test
    void testFirstOrDefault23() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        Predicate<Object> predicate = (Predicate<Object>) mock(Predicate.class);
        when(predicate.test(any())).thenReturn(false);
        assertNull(CollectionHelper.firstOrDefault(objectList, predicate));
        verify(predicate).test(any());
    }

    @Test
    void testForEach() {
        StringTokenizer enumerations = new StringTokenizer("Str");
        Consumer<? super Object> consumer = (Consumer<? super Object>) mock(Consumer.class);
        doNothing().when(consumer).accept(any());
        CollectionHelper.forEach(enumerations, consumer);
        verify(consumer).accept(any());
    }


    @Test
    void testFirst() {
        ArrayList list = new ArrayList<>();
        assertThrows(CommonModuleException.class, () -> CollectionHelper.first(list, "Id"));
        assertThrows(CommonModuleException.class, () -> CollectionHelper.first(list, "Id"));
    }

    @Test
    void testFirst2() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        assertThrows(CommonModuleException.class, () -> CollectionHelper.first(iDomainObjectList, "Id"));
    }

    @Test
    void testFirst3() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        assertTrue(CollectionHelper.first(iDomainObjectList, null) instanceof BatchFailResult);
    }

    @Test
    void testFirst4() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        var obj = new BaseDomainObject.StringDomainObject();

        assertThrows(CommonModuleException.class, () -> CollectionHelper.first(iDomainObjectList, obj));
    }

    @Test
    void testFirst5() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        assertThrows(CommonModuleException.class, () -> CollectionHelper.first(iDomainObjectList, "Id"));
    }

    @Test
    void testFirst6() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        assertTrue(CollectionHelper.first(iDomainObjectList, null) instanceof BatchFailResult);
    }

    @Test
    void testFirst7() {
        ArrayList<IDomainObject<Object>> iDomainObjectList = new ArrayList<>();
        iDomainObjectList.add(new BatchFailResult<>());
        var obj = new BaseDomainObject.StringDomainObject();
        assertThrows(CommonModuleException.class, () -> CollectionHelper.first(iDomainObjectList, obj));
    }

    @Test
    void testCopyOnlyMoreThanZero() {
        assertTrue(CollectionHelper.copyOnlyMoreThanZero(new ArrayList<>()).isEmpty());
        assertTrue(CollectionHelper.copyOnlyMoreThanZero(new ArrayList<>()).isEmpty());
    }

    @Test
    void testCopyOnlyMoreThanZero2() {
        ArrayList<Number> numberList = new ArrayList<>();
        numberList.add(0);
        assertTrue(CollectionHelper.copyOnlyMoreThanZero(numberList).isEmpty());
    }

    @Test
    void testCopyOnlyMoreThanZero3() {
        ArrayList<Number> numberList = new ArrayList<>();
        numberList.add(1);
        assertEquals(1, CollectionHelper.copyOnlyMoreThanZero(numberList).size());
    }

    @Test
    void testCopyOnlyMoreThanZero4() {
        ArrayList<Number> numberList = new ArrayList<>();
        numberList.add(null);
        assertTrue(CollectionHelper.copyOnlyMoreThanZero(numberList).isEmpty());
    }

    @Test
    void testCopyOnlyMoreThanZero5() {
        ArrayList<Number> numberList = new ArrayList<>();
        numberList.add(0);
        assertTrue(CollectionHelper.copyOnlyMoreThanZero(numberList).isEmpty());
    }

    @Test
    void testCopyOnlyMoreThanZero6() {
        ArrayList<Number> numberList = new ArrayList<>();
        numberList.add(1);
        assertEquals(1, CollectionHelper.copyOnlyMoreThanZero(numberList).size());
    }

    @Test
    void testCopyOnlyMoreThanZero7() {
        ArrayList<Number> numberList = new ArrayList<>();
        numberList.add(null);
        assertTrue(CollectionHelper.copyOnlyMoreThanZero(numberList).isEmpty());
    }

    @Test
    void emptyIfNull() {
        ArrayList<Number> list;
        assertThat(CollectionHelper.emptyIfNull(null)).isNotEqualTo(null);

        list = new ArrayList<>(0);
        assertThat(CollectionHelper.emptyIfNull(list)).isEqualTo(list);
    }

    @Test
    void testEmptyIfNull() {
        assertTrue(true);
        assertTrue(CollectionHelper.emptyIfNull(null).isEmpty());
    }
}

