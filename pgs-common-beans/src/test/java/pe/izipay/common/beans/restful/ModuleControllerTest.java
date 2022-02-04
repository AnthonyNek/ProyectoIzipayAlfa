package pe.izipay.common.beans.restful;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import pe.izipay.common.core.exceptions.AppModuleException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ModuleControllerTest {

    private static final ModuleController moduleController = new ModuleController() {};

    @AfterEach
    void removeClaimer() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    void testValidarId() {

        assertThrows(AppModuleException.class, () -> moduleController.validarId("42"));
        assertEquals("999999999999999999999999", moduleController.validarId("999999999999999999999999"));
        assertThrows(AppModuleException.class, () -> moduleController.validarId("42", "Input"));
        assertEquals("999999999999999999999999", moduleController.validarId("999999999999999999999999", "Input"));
    }

    @Test
    void testValidarBlank() {
        assertEquals("Valor", (moduleController).validarBlank("Valor", "Input"));
        assertThrows(AppModuleException.class, () -> moduleController.validarBlank("", "Input"));
    }
}

