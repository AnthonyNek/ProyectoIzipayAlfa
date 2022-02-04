package pe.izipay.common.beans.adapters.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultHeader;
import io.jsonwebtoken.impl.DefaultJws;
import io.jsonwebtoken.impl.DefaultJwsHeader;
import io.jsonwebtoken.impl.DefaultJwtParser;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

class BaseJwtAdapterTest {
    @Test
    void testGetAllClaimsFromToken() {
        DefaultJwtParser defaultJwtParser = mock(DefaultJwtParser.class);
        DefaultJwsHeader header = new DefaultJwsHeader();
        DefaultClaims defaultClaims = new DefaultClaims();
        when(defaultJwtParser.parseClaimsJws(any()))
                .thenReturn(new DefaultJws<>(header, defaultClaims, "Signature"));

        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(defaultJwtParser);
        Claims actualAllClaimsFromToken = rsaJwtAdapter.getAllClaimsFromToken("ABC123");
        assertSame(defaultClaims, actualAllClaimsFromToken);
        assertTrue(actualAllClaimsFromToken.isEmpty());
        verify(defaultJwtParser).parseClaimsJws(any());
    }

    @Test
    void testGetAllClaimsFromTokenSafe() {
        assertNull((new RSAJwtAdapter()).getAllClaimsFromTokenSafe("ABC123"));
    }

    @Test
    void testGetAllClaimsFromTokenSafe2() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(new DefaultJwtParser());
        assertNull(rsaJwtAdapter.getAllClaimsFromTokenSafe("ABC123"));
    }

    @Test
    void testGetAllClaimsFromTokenSafe3() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(new DefaultJwtParser());
        assertNull(rsaJwtAdapter.getAllClaimsFromTokenSafe(""));
    }

    @Test
    void testGetAllClaimsFromTokenIgnoreExpiration() {
        assertNull((new RSAJwtAdapter()).getAllClaimsFromTokenIgnoreExpiration("ABC123"));
    }

    @Test
    void testGetAllClaimsFromTokenIgnoreExpiration2() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(new DefaultJwtParser());
        assertNull(rsaJwtAdapter.getAllClaimsFromTokenIgnoreExpiration("ABC123"));
    }

    @Test
    void testGetAllClaimsFromTokenIgnoreExpiration3() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(new DefaultJwtParser());
        assertNull(rsaJwtAdapter.getAllClaimsFromTokenIgnoreExpiration(""));
    }

    @Test
    void testGetIdentifierFromToken() {
        DefaultJwtParser defaultJwtParser = mock(DefaultJwtParser.class);
        DefaultJwsHeader header = new DefaultJwsHeader();
        when(defaultJwtParser.parseClaimsJws(any()))
                .thenReturn(new DefaultJws<>(header, new DefaultClaims(), "Signature"));

        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(defaultJwtParser);
        assertNull(rsaJwtAdapter.getIdentifierFromToken("ABC123"));
        verify(defaultJwtParser).parseClaimsJws(any());
    }

    @Test
    void testGetIdentifierFromToken2() {
        DefaultJwtParser defaultJwtParser = mock(DefaultJwtParser.class);
        var header = new DefaultHeader<>();
        when(defaultJwtParser.parseClaimsJws(any()))
                .thenThrow(new ExpiredJwtException(header, new DefaultClaims(), "An error occurred"));

        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(defaultJwtParser);
        assertNull(rsaJwtAdapter.getIdentifierFromToken("ABC123"));
        verify(defaultJwtParser).parseClaimsJws(any());
    }

    @Test
    void testGetExpirationDateFromToken() {
        DefaultJwtParser defaultJwtParser = mock(DefaultJwtParser.class);
        DefaultJwsHeader header = new DefaultJwsHeader();
        when(defaultJwtParser.parseClaimsJws(any()))
                .thenReturn(new DefaultJws<>(header, new DefaultClaims(), "Signature"));

        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(defaultJwtParser);
        assertNull(rsaJwtAdapter.getExpirationDateFromToken("ABC123"));
        verify(defaultJwtParser).parseClaimsJws(any());
    }

    @Test
    void testGetExpirationDateFromToken2() {
        DefaultJwtParser defaultJwtParser = mock(DefaultJwtParser.class);
        var header = new DefaultHeader<>();
        when(defaultJwtParser.parseClaimsJws(any()))
                .thenThrow(new ExpiredJwtException(header, new DefaultClaims(), "An error occurred"));

        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(defaultJwtParser);
        assertNull(rsaJwtAdapter.getExpirationDateFromToken("ABC123"));
        verify(defaultJwtParser).parseClaimsJws(any());
    }

    @Test
    void testGetClaimFromToken() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        Function<Claims, Object> function = (Function<Claims, Object>) mock(Function.class);
        when(function.apply(any())).thenReturn("Apply");
        assertEquals("Apply", rsaJwtAdapter.getClaimFromToken("ABC123", function));
        verify(function).apply(any());
    }

    @Test
    void testGetClaimFromToken2() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        Function<Claims, Object> function = (Function<Claims, Object>) mock(Function.class);
        var header = new DefaultHeader<>();
        when(function.apply(any()))
                .thenThrow(new ExpiredJwtException(header, new DefaultClaims(), "An error occurred"));
        assertThrows(ExpiredJwtException.class, () -> rsaJwtAdapter.getClaimFromToken("ABC123", function));
        verify(function).apply(any());
    }

    @Test
    void testGetSubjectExpiration() {
        assertNull((new RSAJwtAdapter()).getSubjectExpiration("ABC123"));
    }

    @Test
    void testGetSubjectExpiration2() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(new DefaultJwtParser());
        assertNull(rsaJwtAdapter.getSubjectExpiration("ABC123"));
    }

    @Test
    void testGetSubjectExpiration3() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(new DefaultJwtParser());
        assertNull(rsaJwtAdapter.getSubjectExpiration(null));
    }

    @Test
    void testIsTokenSigned() {
        assertFalse((new RSAJwtAdapter()).isTokenSigned("ABC123"));
    }

    @Test
    void testIsTokenSigned2() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(new DefaultJwtParser());
        assertFalse(rsaJwtAdapter.isTokenSigned("ABC123"));
    }

    @Test
    void testIsTokenSigned3() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(new DefaultJwtParser());
        assertFalse(rsaJwtAdapter.isTokenSigned(null));
    }

    @Test
    void testValidateToken() {
        assertFalse((new RSAJwtAdapter()).validateToken("ABC123"));
    }

    @Test
    void testValidateToken2() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(new DefaultJwtParser());
        assertFalse(rsaJwtAdapter.validateToken("ABC123"));
    }

    @Test
    void testValidateToken3() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        rsaJwtAdapter.setParser(new DefaultJwtParser());
        assertFalse(rsaJwtAdapter.validateToken(null));
    }
}

