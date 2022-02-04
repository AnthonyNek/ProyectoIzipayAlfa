package pe.izipay.common.core.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import pe.izipay.common.core.exceptions.AppRuntimeException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class TextHelperTest {
    @Test
    void testCastBasicType() throws AppRuntimeException {
        assertDoesNotThrow(() ->{
            TextHelper.castBasicType("42", Object.class);
        });
    }

    @Test
    void testCastBasicType2() throws AppRuntimeException {
        assertDoesNotThrow(() ->{
        TextHelper.castBasicType("Input", Boolean.class);
        });
    }

    @Test
    void testCastBasicType3() throws AppRuntimeException {
        assertDoesNotThrow(() ->{
            TextHelper.castBasicType("42", Byte.class);
        });
    }

    @Test
    void testCastBasicType4() throws AppRuntimeException {
        assertDoesNotThrow(() ->{
            TextHelper.castBasicType("42", Double.class);
        });
    }

    @Test
    void testCastBasicType5() throws AppRuntimeException {
        assertDoesNotThrow(() ->{
            TextHelper.castBasicType("42", Float.class);
        });
    }

    @Test
    void testCastBasicType6() throws AppRuntimeException {
        assertDoesNotThrow(() ->{
            TextHelper.castBasicType("42", Long.class);
        });
    }

    @Test
    void testCastBasicType7() throws AppRuntimeException {
        assertDoesNotThrow(() ->{
            TextHelper.castBasicType("42", Short.class);
        });
    }

    @Test
    void testCastBasicTypeSafe() {
        int actualValue = Integer.MAX_VALUE, defaultValue = Integer.MAX_VALUE;
        assertEquals(actualValue, TextHelper.castBasicTypeSafe(String.valueOf(actualValue), Integer.class, defaultValue));
        assertEquals(defaultValue, TextHelper.castBasicTypeSafe("Input", Integer.class, defaultValue));
    }

    @Test
    void testCastBasicTypeSafe2() {
        assertEquals(true, TextHelper.castBasicTypeSafe(String.valueOf(true), Boolean.class, false));
        assertEquals(false, TextHelper.castBasicTypeSafe(null, Boolean.class, false));
        assertEquals(false, TextHelper.castBasicTypeSafe(null, Object.class, false));
    }

    @Test
    void testCastBasicTypeSafe3() {
        byte actualValue = Byte.MIN_VALUE, defaultValue = Byte.MAX_VALUE;
        assertEquals(actualValue, TextHelper.castBasicTypeSafe(String.valueOf(actualValue), Byte.class, defaultValue));
        assertEquals(defaultValue, TextHelper.castBasicTypeSafe(null, Byte.class, defaultValue));
        assertEquals(defaultValue, TextHelper.castBasicTypeSafe(null, Object.class, defaultValue));
    }

    @Test
    void testCastBasicTypeSafe4() {
        short actualValue = Short.MIN_VALUE, defaultValue = Short.MAX_VALUE;
        assertEquals(actualValue, TextHelper.castBasicTypeSafe(String.valueOf(actualValue), Short.class, defaultValue));
        assertEquals(defaultValue, TextHelper.castBasicTypeSafe(null, Short.class, defaultValue));
        assertEquals(defaultValue, TextHelper.castBasicTypeSafe(null, Object.class, defaultValue));
    }

    @Test
    void testCastBasicTypeSafe5() {
        long actualValue = Long.MAX_VALUE, defaultValue = Long.MIN_VALUE;
        assertEquals(actualValue, TextHelper.castBasicTypeSafe(String.valueOf(actualValue), Long.class, defaultValue));
        assertEquals(defaultValue, TextHelper.castBasicTypeSafe(null, Long.class, defaultValue));
        assertEquals(defaultValue, TextHelper.castBasicTypeSafe(null, Object.class, defaultValue));
    }

    @Test
    void testCastBasicTypeSafe6() {
        float actualValue = Float.MAX_VALUE, defaultValue = Float.MIN_VALUE;
        assertEquals(actualValue, TextHelper.castBasicTypeSafe(String.valueOf(actualValue), Float.class, defaultValue));
        assertEquals(defaultValue, TextHelper.castBasicTypeSafe(null, Float.class, defaultValue));
        assertEquals(defaultValue, TextHelper.castBasicTypeSafe(null, Object.class, defaultValue));
    }

    @Test
    void testCastBasicTypeSafe7() {
        double actualValue = Double.MAX_VALUE, defaultValue = Double.MIN_VALUE;
        assertEquals(actualValue, TextHelper.castBasicTypeSafe(String.valueOf(actualValue), Double.class, defaultValue));
        assertEquals(defaultValue, TextHelper.castBasicTypeSafe(null, Double.class, defaultValue));
        assertEquals(defaultValue, TextHelper.castBasicTypeSafe(null, Object.class, defaultValue));
    }

    @Test
    void testCastBasicTypeSafe8() {
        assertEquals("Default Value", TextHelper.castBasicTypeSafe("Input", null, "Default Value"));
    }

    @Test
    void testIsNullOrEmpty() {
        assertFalse(TextHelper.isNullOrEmpty("42"));
        assertTrue(true);
        assertTrue(TextHelper.isNullOrEmpty(""));
    }

    @Test
    void testToStringNullable() {
        assertEquals("Value", TextHelper.toStringNullable("Value"));
        assertEquals("", TextHelper.toStringNullable(null));
    }

    @Test
    void testGetNullIfEmpty() {
        assertEquals("42", TextHelper.getNullIfEmpty("42"));
        assertNull(null);
        assertNull(TextHelper.getNullIfEmpty(""));
    }

    @Test
    void testRemoveIfEndWith() {
        assertEquals("42", TextHelper.removeIfEndWith("42", 'A'));
        assertEquals("4", TextHelper.removeIfEndWith("42", '2'));
        assertEquals("42", TextHelper.removeIfEndWith("42", "End"));
        assertEquals("", TextHelper.removeIfEndWith("foo", "foo"));
    }

    @Test
    void testGenerateUUID() {
        assertDoesNotThrow(TextHelper::generateUUID);
    }

    @Test
    void testGenerate() {
        Assertions.assertDoesNotThrow(() ->
                TextHelper.generate("jane.doe@example.org", 1));
    }

    @Test
    void testGenerateAlphaNumeric() {
        Assertions.assertDoesNotThrow(() ->
                TextHelper.generateAlphaNumeric(1));
    }

    @Test
    void testGenerateNumeric() {
        Assertions.assertDoesNotThrow(() -> TextHelper.generateNumeric(1));
    }

    @Test
    void testLength() {
        assertEquals(2, TextHelper.length("42"));
        assertEquals(0, TextHelper.length(null));
    }

    @Test
    void testToBase64Encode() {
        assertEquals("NDI=", TextHelper.toBase64Encode("42"));
        assertEquals("QUFBQUFBQUE=", TextHelper.toBase64Encode("AAAAAAAA".getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void testToBase64Decode() {
        byte[] actualToBase64DecodeResult = TextHelper.toBase64Decode("42");
        assertEquals(1, actualToBase64DecodeResult.length);
//        assertEquals('￣', actualToBase64DecodeResult[0]);
        assertEquals(-29, actualToBase64DecodeResult[0]);
    }

    @Test
    void testToBase64DecodeString() {
        var value = "42";
        var encoded = TextHelper.toBase64Encode(value);
        assertEquals(value, TextHelper.toBase64DecodeString(encoded));
    }

    @Test
    void testTrim() {
        assertEquals("42", TextHelper.trim("42"));
        assertEquals("", TextHelper.trim(null));
    }

    @Test
    void testDefaultValue() {
        assertEquals("42", TextHelper.defaultValue("42"));
        assertEquals("", TextHelper.defaultValue(null));
    }

    @Test
    void testGenerateRandomSecret() {
        assertDoesNotThrow((Executable) TextHelper::generateRandomSecret);
    }

    @Test
    void testJoin() {
        assertEquals("", TextHelper.join("Separator", new ArrayList<>()));
        assertEquals("", TextHelper.join("foo", (Collection<?>) null));
        assertEquals("Values", TextHelper.join("Separator", "Values"));
        assertEquals("", TextHelper.join("foo", (Object[]) null));
        assertEquals("", TextHelper.join("Separator", (Object) null));
        assertEquals("", TextHelper.join("Separator"));
    }

    @Test
    void testJoin2() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("42");
        assertEquals("42", TextHelper.join("Separator", objectList));
    }

    @Test
    void testJoin3() {
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.add("");
        assertEquals("", TextHelper.join("Separator", objectList));
    }
}

