package pe.izipay.common.beans.middlewares.security;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


class IDefaultCorsConfigTest {

    @Test
    void testDefaultConfig() {

        var defaultConfig = new IDefaultCorsConfig() {};

        assertEquals("/**", defaultConfig.getMapping());
        assertFalse(defaultConfig.getCredentials());
        assertArrayEquals(new String[] { "HEAD", "GET", "POST", "PUT", "DELETE", "PATCH" }, defaultConfig.getAllowedMethods());
        assertArrayEquals(new String[] { "*" }, defaultConfig.getAllowedHeaders());
        assertArrayEquals(new String[] { "*" }, defaultConfig.getAllowedOrigins());

        var webMvcConfig = defaultConfig.createWebMvcConfigurer();
        webMvcConfig.addCorsMappings(new CorsRegistry());
        assertNull(webMvcConfig.getValidator());

    }
}
