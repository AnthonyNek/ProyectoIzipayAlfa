package pe.izipay.common.beans.middlewares.security;

import java.io.IOException;
import javax.servlet.http.HttpServletRequestWrapper;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AccountExpiredException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JwtAuthEntryPointTest {
    @Test
    void testCommence() throws IOException {
        JwtAuthEntryPoint jwtAuthEntryPoint = new JwtAuthEntryPoint();
        HttpServletRequestWrapper request = new HttpServletRequestWrapper(new HttpServletRequestWrapper(
                new HttpServletRequestWrapper(new HttpServletRequestWrapper(new MockHttpServletRequest()))));
        MockHttpServletResponse response = new MockHttpServletResponse();
        assertDoesNotThrow(() ->
                jwtAuthEntryPoint.commence(request, response, new AccountExpiredException("Msg"))
        );
    }
}

