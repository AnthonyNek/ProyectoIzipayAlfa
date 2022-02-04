package pe.izipay.common.beans.middlewares.security;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.connector.Response;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import pe.izipay.common.beans.adapters.jwt.RSAJwtAdapter;

class AuthHeaderFilterTest {
    @Test
    void testConstructor() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        AuthHeaderFilter actualAuthHeaderFilter = new AuthHeaderFilter(rsaJwtAdapter, true);

        assertTrue(actualAuthHeaderFilter
                .getEnvironment() instanceof org.springframework.web.context.support.StandardServletEnvironment);
        assertNull(actualAuthHeaderFilter.getFilterConfig());
        assertNull(rsaJwtAdapter.getPrivateKey());
    }

    @Test
    void testConstructor2() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        AuthHeaderFilter actualAuthHeaderFilter = new AuthHeaderFilter(rsaJwtAdapter, false);

        assertTrue(actualAuthHeaderFilter
                .getEnvironment() instanceof org.springframework.web.context.support.StandardServletEnvironment);
        assertNull(actualAuthHeaderFilter.getFilterConfig());
        assertNull(rsaJwtAdapter.getPrivateKey());
    }

    @Test
    void testConstructor3() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        AuthHeaderFilter actualAuthHeaderFilter = new AuthHeaderFilter(rsaJwtAdapter, true, "Claim", "Value");

        assertTrue(actualAuthHeaderFilter
                .getEnvironment() instanceof org.springframework.web.context.support.StandardServletEnvironment);
        assertNull(actualAuthHeaderFilter.getFilterConfig());
        assertNull(rsaJwtAdapter.getPrivateKey());
    }

    @Test
    void testConstructor4() {
        RSAJwtAdapter rsaJwtAdapter = new RSAJwtAdapter();
        AuthHeaderFilter actualAuthHeaderFilter = new AuthHeaderFilter(rsaJwtAdapter, false, "Claim", "Value");

        assertTrue(actualAuthHeaderFilter
                .getEnvironment() instanceof org.springframework.web.context.support.StandardServletEnvironment);
        assertNull(actualAuthHeaderFilter.getFilterConfig());
        assertNull(rsaJwtAdapter.getPrivateKey());
    }

    @Test
    void testConstructor5() {
        RSAJwtAdapter jwtAdapter = new RSAJwtAdapter();
        HashMap<String, Object> stringObjectMap = new HashMap<>();
        new AuthHeaderFilter(jwtAdapter, true, stringObjectMap);

        assertTrue(stringObjectMap.isEmpty());
    }

    @Test
    void testConstructor6() {
        RSAJwtAdapter jwtAdapter = new RSAJwtAdapter();
        HashMap<String, Object> stringObjectMap = new HashMap<>();
        new AuthHeaderFilter(jwtAdapter, false, stringObjectMap);

        assertTrue(stringObjectMap.isEmpty());
    }

    @Test
    void testConstructor7() {
        RSAJwtAdapter jwtAdapter = new RSAJwtAdapter();
        MockClaimsAction mockClaimsAction = mock(MockClaimsAction.class);
        when(mockClaimsAction.action()).thenReturn(new HashMap<>());
        new AuthHeaderFilter(jwtAdapter, true, mockClaimsAction);

        verify(mockClaimsAction).action();
    }

    @Test
    void testConstructor8() {
        RSAJwtAdapter jwtAdapter = new RSAJwtAdapter();
        MockClaimsAction mockClaimsAction = mock(MockClaimsAction.class);
        when(mockClaimsAction.action()).thenReturn(new HashMap<>());
        new AuthHeaderFilter(jwtAdapter, false, mockClaimsAction);

        verify(mockClaimsAction).action();
    }

    @Test
    void testDoFilterInternal() throws IOException, ServletException {
        AuthHeaderFilter authHeaderFilter = new AuthHeaderFilter(new RSAJwtAdapter(), true);
        HttpServletRequestWrapper request = new HttpServletRequestWrapper(new HttpServletRequestWrapper(
                new HttpServletRequestWrapper(new HttpServletRequestWrapper(new MockHttpServletRequest()))));
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(any(), any());
        authHeaderFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(any(), any());
    }

    @Test
    void testDoFilterInternal2() throws IOException, ServletException {
        AuthHeaderFilter authHeaderFilter = new AuthHeaderFilter(new RSAJwtAdapter(), false);
        HttpServletRequestWrapper request = new HttpServletRequestWrapper(new HttpServletRequestWrapper(
                new HttpServletRequestWrapper(new HttpServletRequestWrapper(new MockHttpServletRequest()))));
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(any(), any());
        authHeaderFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(any(), any());
    }
}

