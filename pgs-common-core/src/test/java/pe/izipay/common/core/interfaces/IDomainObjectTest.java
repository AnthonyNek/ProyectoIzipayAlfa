package pe.izipay.common.core.interfaces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pe.izipay.common.core.domainobjects.BaseDomainObject;

class IDomainObjectTest {
    @Test
    void testHashCodeId() {
        Assertions.assertEquals(31, (new BaseDomainObject.StringDomainObject()).hashCodeId());
    }

    @Test
    void testEqualsId() {
        Assertions.assertFalse((new BaseDomainObject.StringDomainObject()).equalsId("Obj"));
        Assertions.assertFalse((new BaseDomainObject.StringDomainObject()).equalsId(null));
    }

    @Test
    void testEqualsId2() {
        var defaultlUnidadPoliticaValueObject = new BaseDomainObject.StringDomainObject();
        Assertions.assertTrue(
                defaultlUnidadPoliticaValueObject.equalsId(new BaseDomainObject.StringDomainObject()));
    }
}

