package pe.izipay.common.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FieldValidationTest {
    @Test
    void testHasCharsEspecial() {
        assertFalse(FieldValidation.hasCharsEspecial('A'));
        assertTrue(FieldValidation.hasCharsEspecial('?'));
    }
}

